package com.yy.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.server.common.CusOriginEnum;
import com.yy.server.common.ErrorEnum;
import com.yy.server.common.SMSEnum;
import com.yy.server.mapper.AreaMapper;
import com.yy.server.mapper.CustomerMapper;
import com.yy.server.mapper.DeviceInfoMapper;
import com.yy.server.model.Area;
import com.yy.server.model.Customer;
import com.yy.server.model.DeviceInfo;
import com.yy.server.model.logic.CustomerAndCountry;
import com.yy.server.model.view.CustRegVO;
import com.yy.server.model.view.CustSelVO;
import com.yy.server.model.view.PageVO;
import com.yy.server.util.DateUtils;
import com.yy.server.util.PasswordUtil;
import com.yy.server.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yy.server.common.ErrorEnum.ERROR_21004;
import static com.yy.server.common.ErrorEnum.ERROR_21005;

@Service
@Transactional
public class CustomerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private AppShopService appShopService;

    @Autowired
    private AreaMapper areaMapper;

    @Value("${sms.reg.prefix}")
    private String smsPrefix;

    @Value("${sms.forget.prefix}")
    private String forgetPrefix;

    @Value("${sms.bind.prefix}")
    private String bindPrefix;

    @Value("${aliyun.mail.reg.MRprefix}")
    private String MRprefix;

    @Value("${aliyun.mail.forget.MFprefix}")
    private String MFprefix;

    @Transactional
    public Boolean reg(CustRegVO vo) throws Exception {
        //是否是节点用户
        String account = vo.getAccount();
        String code = vo.getSmsCode();

        String redisKey = "";
        String redisCode = "";

        if (account.contains("@")) {
            redisKey = MRprefix + "+" + SMSEnum.REGIST + "+" + vo.getSmsCode() + account;
            redisCode = (String) redisTemplate.opsForValue().get(redisKey);
        } else {
            redisKey = smsPrefix + "+" + vo.getCountryCode() + vo.getAccount();
            redisCode = (String) redisTemplate.opsForValue().get(redisKey);
        }

//        if (StringUtils.isEmpty(redisCode)) {
//            throw new Exception(ERROR_21003.getCode() + "");
//        } else if (!redisCode.equals(code)) {
//            throw new Exception(ERROR_21002.getCode() + "");
//        } else {
//            redisTemplate.delete(redisKey);
//        }

        Customer cus = findByAccount(vo.getAccount(), "");
        if (cus != null) {
            throw new Exception(ERROR_21004.getCode() + "");
        }

        //插入的实体对象
        Customer entity = new Customer();

        entity.setIdentity("1");
        entity.setInviteCode(vo.getInviteCode());
        Date date = new Date();
        String custId = UuidUtil.getUUID();
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();

        //entity.setRegFlag(vo.getRegFlag());
        entity.setAccount(vo.getAccount());
        entity.setCreatedTime(date);
        entity.setUpdatedTime(date);
        entity.setState(true);
        entity.setSalt(salt);
        entity.setCountryId(vo.getCountryId());
        String password = PasswordUtil.createCustomPwd(vo.getPasswd(), entity.getSalt());
        entity.setPassword(password);

        if (account.contains("@")) {
            entity.setEmail(account);
            entity.setRegWay(1);
        } else {
            entity.setAccount(vo.getAccount());
            entity.setAllPhone("+" + vo.getCountryCode() + vo.getAccount());
            entity.setRegWay(0);
        }
        customerMapper.insertSelective(entity);
        return true;
    }

    /**
     * 账号和appId 查询账号
     *
     * @param account
     * @param appId
     * @return
     */
    public Customer findByAccount(String account, String appId) {
        Weekend<Customer> weekend = Weekend.of(Customer.class);
        WeekendCriteria<Customer, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(Customer::getAccount, account);
        //criteria.andEqualTo(Customer::getAppId, appId);
        return customerMapper.selectOneByExample(weekend);
    }

    /**
     * 用户custSelVO查询账号列表
     *
     * @param custSelVO
     * @return
     */
    public List<CustomerAndCountry> findByCustSelVO(CustSelVO custSelVO) {
        List<CustomerAndCountry> customers = customerMapper.getCustomerList(custSelVO);
        return customers;
    }

    /**
     * 查看用户列表
     *
     * @param
     * @return
     */
    public HashMap<String, Object> getCustomers(CustSelVO custSelVO) {
        List<CustomerAndCountry> customers = null;
        if (custSelVO.getPage() != null && custSelVO.getRows() != null) {
            PageHelper.startPage(custSelVO.getPage(), custSelVO.getRows());
        }
        customers = customerMapper.getCustomerList(custSelVO);
        PageInfo pageinfo = new PageInfo(customers);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("customers", customers);
        return map;
    }

    /**
     * 查看用户列表
     *
     * @param
     * @return
     */
    public HashMap<String, Object> getCusts(CustSelVO custSelVO) {
        if (custSelVO.getPage() != null && custSelVO.getRows() != null) {
            PageHelper.startPage(custSelVO.getPage(), custSelVO.getRows());
        }
        List<CustomerAndCountry> customers = customerMapper.getCusts(custSelVO);
        PageInfo pageinfo = new PageInfo(customers);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("customers", customers);
        return map;
    }


    public CustomerAndCountry getBasicCustomerInfo(int id) {
        CustomerAndCountry customerInfo = customerMapper.getCustomerInfo(id);
        return customerInfo;
    }

    public void updCustomerInfo(Map map) {
        customerMapper.updCustomerInfo(map);
    }


    public void batchUpdateCustomerInfo(List<Integer> ids) {
        if (ids != null && ids.size() > 0) {
            for (int id : ids) {
                Customer customer = new Customer();
                customer.setId(id);
                customer.setState(false);
                customerMapper.updateByPrimaryKeySelective(customer);
            }
        }
    }


    public String getInviteCode() {
        char[] chs = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                'W', 'X', 'Y', 'Z'};
        SecureRandom random = new SecureRandom();
        final char[] value = new char[8];
        for (int i = 0; i < value.length; i++) {
            value[i] = (char) chs[random.nextInt(chs.length)];
        }
        final String code = new String(value);
        //查询数据库
        Customer cus = customerMapper.getTheCustByCode(code);

        if (cus == null) {
            return code;
        } else {
            return getInviteCode();
        }

    }

    public HashMap<String, Object> getInviteList(PageVO vo) {
        Subject currentUser = SecurityUtils.getSubject();
        Customer cus = (Customer) currentUser.getPrincipal();
        String code = cus.getSelfInvite();

        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }

        //查询被邀请的用户列表
        List<Customer> list = customerMapper.getInvitedCustomers(code);
        PageInfo pageinfo = new PageInfo(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", pageinfo.getTotal());
        map.put("customers", list);
        return map;


    }

    public HashMap<String, Object> getInvitors(CustSelVO custSelVO) {
        String code = custSelVO.getInviteCode();
        String appId = custSelVO.getAppId();
        if (custSelVO.getPage() != null && custSelVO.getRows() != null) {
            PageHelper.startPage(custSelVO.getPage(), custSelVO.getRows());
        }

        //查询被邀请的用户列表
        List<CustomerAndCountry> list = customerMapper.getInvitors(custSelVO);
        PageInfo pageinfo = new PageInfo(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", pageinfo.getTotal());
        map.put("customers", list);
        return map;
    }

    public CustomerAndCountry getCustInfo(int id) {
        CustomerAndCountry custInfo = customerMapper.getCustomerInfo(id);
        return custInfo;
    }

    public HashMap<String, Object> checkDevice(String custId, String deviceId, String account, String appId) {
        HashMap<String, Object> map = new HashMap<>();
        String now = DateUtils.getDateTime();
        DeviceInfo device = new DeviceInfo();
        map.put("repalceFlag", 0);
        map.put("account", null);
        return map;
    }


    @Transactional
    public Boolean saveCustomer(CustRegVO vo) throws Exception {
        Customer cus = findByAccount(vo.getAccount(), vo.getAppId());
        if (cus != null) {
            throw new Exception(ErrorEnum.ERROR_21004.getCode() + "");
        }

        //默认生成初始密码：123456
        if (StringUtils.isBlank(vo.getPasswd())) {
            vo.setPasswd("123456");
        }
        String date = DateUtils.getDateTime();
        Customer entity = new Customer();

        entity.setAccount(vo.getAccount());
        entity.setCreatedTime(new Date());
        entity.setUpdatedTime(new Date());
        entity.setState(true);
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        String password = PasswordUtil.createCustomPwd(vo.getPasswd(), entity.getSalt());
        entity.setSalt(salt);
        entity.setPassword(password);
        entity.setCountryId(vo.getCountryId());
        entity.setInviteCode(vo.getInviteCode());
        entity.setInviteCode(vo.getInviteCode());
        entity.setOrigin(CusOriginEnum.FORMAPP.getValue());

        // 生成自己的邀请码
        String selfCode = getInviteCode();
        entity.setSelfInvite(selfCode);
        String account = vo.getAccount();

        if (account.contains("@")) {
            entity.setEmail(account);
            entity.setRegWay(1);
        } else {
            entity.setAllPhone("+" + vo.getCountryCode() + vo.getAccount());
            entity.setRegWay(0);
        }
        entity.setAppId(vo.getAppId());
        customerMapper.insertSelective(entity);
        return true;
    }


    public List<Area> getprovinces() {
        long begin = System.currentTimeMillis();
        List<Area> province = areaMapper.getprovinces();
        long middle = System.currentTimeMillis();
        logger.info("查询省份用时:" + (middle - begin) + "ms");
        return province;
    }

    public List<Area> getcities(String provinceId) {
        List<Area> cities = areaMapper.getcities(provinceId);
        return cities;
    }


    public Boolean forget(CustRegVO vo) throws Exception {
        String code = vo.getSmsCode();
        String account = vo.getAccount();
//        String redisKey = "";
//        String redisCode = "";
//        if (account.contains("@")) {
//            redisKey = MFprefix + "+" + SMSEnum.FORGET + "+" + vo.getSmsCode() + account;
//        } else {
//            redisKey = forgetPrefix + "+" + vo.getCountryCode() + vo.getAccount();
//        }
//        redisCode = (String) redisTemplate.opsForValue().get(redisKey);
//        if (null == redisCode) {
//            throw new Exception(ERROR_21003.getCode() + "");
//        } else if (!redisCode.equals(code)) {
//            throw new Exception(ERROR_21002.getCode() + "");
//        } else {
//            redisTemplate.delete(redisKey);
//        }
        Customer customer = new Customer();
        if (account.contains("@")) {
            Weekend<Customer> weekend = Weekend.of(Customer.class);
            WeekendCriteria<Customer, Object> criteria = weekend.weekendCriteria();
            criteria.andEqualTo(Customer::getEmail, vo.getAccount());
            customer = customerMapper.selectOneByExample(weekend);

        } else {
            Weekend<Customer> weekend = Weekend.of(Customer.class);
            WeekendCriteria<Customer, Object> criteria = weekend.weekendCriteria();
            criteria.andEqualTo(Customer::getAccount, vo.getAccount());
            customer = customerMapper.selectOneByExample(weekend);
        }
        if (null == customer) {
            throw new Exception(ERROR_21005.getCode() + "");
        }
        String password = PasswordUtil.createCustomPwd(vo.getPasswd(), customer.getSalt());
        Customer upedObj = new Customer();
        upedObj.setId(customer.getId());
        upedObj.setPassword(password);
        customerMapper.updateByPrimaryKeySelective(upedObj);
        return true;
    }

}
