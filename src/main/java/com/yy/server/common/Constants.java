package com.yy.server.common;


/**
 * 系统常量接口
 *
 * @author ZHOUHUI
 */
public interface Constants {
    /**
     * 结售汇旅行key PM_CODE
     */
    String KEY_EXCHANGE_TYPE = "EXCHANGE_TYPE";
    /**
     * 售汇
     */
    String EXCHANGE_TYPE_BUY = "S";
    /**
     * 结汇
     */
    String EXCHANGE_TYPE_SAIL = "J";

    /**
     * 预约
     */
    String RESERVATION = "1";
    /**
     * 在线支付
     */
    String NON_RESERVATION = "0";

    //--------资金属性---------
    /**
     * 现金
     */
    String CASH_M01 = "M01";
    /**
     * 旅行支票
     */
    String TRAVELER_CHEQUE_M02 = "M02";
    /**
     * 刷卡支付
     */
    String CARD_PAY_M03 = "M03";
    /**
     * 在线支付
     */
    String ONLINE_PAY_M04 = "M04";
    /**
     * 在线预约
     */
    String ONLINE_PAY_M05 = "M05";


    //------------订单状态-----------
    /**
     * 订单状态key  PM_CODE
     */
    String KEY_ORDER_STATUS = "ORDER_STATUS";
    /**
     * 验证中
     */
    String ORDER_STATUS_WAITVALIDATE = "0";
    /**
     * 待付款
     */
    String ORDER_STATUS_WAITPAY = "1";
    /**
     * 未通过验证
     */
    String ORDER_STATUS_NOPASS = "2";
    /**
     * 待发货
     */
    String ORDER_STATUS_WAITSEND = "3";
    /**
     * 已发货
     */
    String ORDER_STATUS_DELIVERY = "4";
    /**
     * 待提取
     */
    String ORDER_STATUS_WAITFETCH = "5";
    /**
     * 已完成
     */
    String ORDER_STATUS_FINISH = "6";
    /**
     * 已取消
     */
    String ORDER_STATUS_CANCEL = "7";
    /**
     * 已支付
     */
    String ORDER_STATUS_OVERPAY = "8";
    /**
     * 退款中
     */
    String ORDER_STATUS_REBUND = "9";
    /**
     * 退款成功
     */
    String ORDER_STATUS_REBUNDFINISH = "10";
    /**
     * 待配钞
     */
    String ORDER_STATUS_WAITDELIVERY = "11";
    /**
     * 待送达提取点
     */
    String ORDER_STATUS_WAITREACH = "12";
    /**
     * 配送中
     */
    String ORDER_STATUS_UNDERDELIVERY = "13";
    /**
     * 交易未成功
     */
    String ORDER_STATUS_DEALFAIL = "14";
    /**
     * 已关闭
     */
    String ORDER_STATUS_NONPAY = "15";
    /**
     * 待审核
     */
    String ORDER_STATUS_WAITAUDIT = "16";
    /**
     * 审核中
     */
    String ORDER_STATUS_UNDERREVIEW = "17";

    //------------删除标志线
    String DELETE_Y = "Y";
    String DELETE_N = "N";

    //---------收费方式
    /**
     * 收费方式
     */
    String KEY_CHARGE_CONDITIONS = "CHARGE_CONDITIONS";
    /**
     * 现钞结汇收费
     */
    String CHARGE_CONDITIONS_J = "J";
    /**
     * 现钞售汇收费
     */
    String CHARGE_CONDITIONS_S = "S";
    /**
     * 旅支收费
     */
    String CHARGE_CONDITIONS_LZ = "LZ";
    /**
     * 刷卡收费
     */
    String CHARGE_CONDITIONS_SK = "SK";
    /**
     * 在线支付收费
     */
    String CHARGE_CONDITIONS_ZX = "ZX";
    /**
     * 预约结汇
     */
    String CHARGE_CONDITIONS_OJ = "OJ";
    /**
     * 预约售汇
     */
    String CHARGE_CONDITIONS_OS = "OS";
    /**
     * 登录常量
     */
    String LOGIN_STATUS_5 = "5";//正常登录，且有权限
    String LOGIN_STATUS_1 = "1";//用户名或密码错误
    String LOGIN_STATUS_2 = "2";//网店机构号错误
    String LOGIN_STATUS_3 = "3";//无任何权限不可登录
    String LOGIN_STATUS_4 = "4";//无任何数据，请填写信息
    String LOGIN_STATUS_6 = "6";//用户暂停使用
    String LOGIN_STATUS_7 = "7";//此用户已登录
    String LOGIN_STATUS_8 = "8";//此用户已登录
    String LOGIN_STATUS_0 = "0";//异常
    String LOGIN_STATUS_9 = "9";//进入后台登录
    //用户状态
    String STOP_Y = "Y";//停止使用
    String STOP_N = "N";//可用
    //返回成功/失败
    String RESULT_Y = "Y";//成功
    String RESULT_N = "N";//失败


    String DEFAULT_PWD = "888888"; //初始化密码
    //--------------证件类型
    /**
     * 证件类型key
     */
    String KEY_ID_TYPE = "ID_TYPE";

    /**
     * 外管客户标志key
     */
    String KEY_SAFECUS_FLAG = "SAFECUS_FLAG";

    //------------渠道
    /**
     * 易兑核心系统
     */
    String CHANNEL_CORE_SYSTEM = "CR0001";
    /**
     * 网点/用户角色
     */
    String PRIVILAGE_FLAG_USEROLE = "USERROLE";//用户角色
    String PRIVILAGE_FLAG_BRANCH = "BRANCH";//机构
    String PRIVILAGE_FLAG_CH = "CH";//渠道
    String PRIVILAGE_FLAG_USER = "USER";//用户
    String PRIVILAGE_FLAG_MENU = "MNU";//权限-菜单
    String PRIVILAGE_FLAG_BTN = "BTN";//权限-按钮
    String PRIVILAGE_FLAG_USR = "USR";//权限-用户
    String PRIVILAGE_FLAG_RLE = "RLE";//权限-角色
    String PRIVILAGE_FLAG_BRH = "BRH";//权限-角色
    String PRIVILAGE_FLAG_WORK = "WORK";
    String PRIVILAGE_FLAG_ETM = "ETM";//网站图片标识

    String PRIVILAGE_FLAG_PRODUCT = "PDT";//权限-产品类别


    String FLAG_SUCCEE = "0";
    String FLAG_FAIL = "1";
    String FLAG_USR_FAIL = "2";
    String FLAG_NULL_FAIL = "3";
    //上传图片路径/easyexchange-trade
    public static final String TECHNICAL_IMGS_SOURCE_PATH = "/src/main/webapp/images/coustomerImg";
    //-------------交易状态
    /**
     * 正常
     */
    String TRADE_STATE_NORMAL = "1";
    /**
     * 冲正
     */
    String TRADE_STATE_RECOVER = "99";
    /**
     * 取消
     */
    String TRADE_STATE_CANCEL = "2";

    //--------------------------机构类型---------------------------------------
    String BRANCH_TYPE_COM = "BT0001";        // 总公司
    String BRANCH_TYPE_SON = "BT0002";        // 分公司
    String BRANCH_TYPE_CENTER = "BT0003";    // 运营中心
    String BRANCH_TYPE_BRANCH = "BT0004";    // 运营网点
    String BRANCH_TYPE_CH = "CH0001";        // 内部渠道
    String BRANCH_TYPE_CH0000 = "CH0000";
    // 密码修改时间
    String PASSWORD_EXPIRETIME = "US0001";
    String PASSWORD_EXPIRE = "US0002";


    //--------流水号前缀
    String SERIALNO_PREFIX_EXG = "bu.exchange";
    String SERIALNO_PREFIX_BRH = "pm.branch"; //机构前缀
    String SERIALNO_PREFIX_PAYACCT = "pm.payacct"; //银行备付金账户前缀
    String SERIALNO_PREFIX_REGION = "pm.region"; //区域信息前缀
    String SERIALNO_PREFIX_PEERBRH = "pm.peerbranch";//同业机构前缀

    //缓存地址 IP  缓存 port
    String MEMCACHED_SWITCH = "MEMCACHED_SWITCH"; //缓存开关，是否创建客户端

    String MEMCACHEDIP_PORT = "192.168.8.150:12000";
    //图片状态
    String IMAGES_STATUS_LLEGAL = "1";//不符合规则，类型
    String IMAGES_STATUS_FILE = "2";//失败
    String IMAGES_STATUS_OVERSIZE = "3";//超过1M，过大
    String IMAGE_PATH_SMALL = "_small";//小图
    String IMAGE_PATH_SAMLL = "_small";//小图
    String IMAGE_PATH_REAL = "_big";//大图
    String LOCAL_ADDRESS = "C:/zp.bmp";//身份证读卡器的图片固定路径
    //客户限额分组
    String CLIENTLIMIT = "CLIENTLIMIT";

    //FTP链接
    String FTP_HOSTNAME = "FTP_HOSTNAME";
    String FTP_IMAGES_ROOTPATH = "FTP_IMAGES_ROOTPATH";//真实

    String FTP_EXCEL_ROOTPATH = "FTP_EXCEL_ROOTPATH";
    String FTP_USERNAME = "FTP_USERNAME";
    String FTP_PASSWORD = "FTP_PASSWORD";
    String FTP_PORT = "FTP_PORT";

    //用户单点登录
    String USERLOGIN_SSO = "USERLOGIN_SSO";

    //---------------网点可交易汇率标志
    /**
     * 是否允许修改汇率
     */
    String ALLOWCHGRATE_Y = "Y";
    /**
     * 结汇买入
     */
    String ALLOWCHGRATE_B = "B";//买入
    /**
     * 售汇卖出
     */
    String ALLOWCHGRATE_S = "S";//卖出
    /**
     * 预约订单状态
     */
    String ORDER_ST_FLAG = "ORDER_ST";
    String ORDER_ST_1 = "1";
    String ORDER_ST_2 = "2";
    String ORDER_ST_3 = "3";
    String ORDER_ST_4 = "4";
    String ORDER_ST_5 = "5";
    /**
     * 预约订单来源方式
     */
    String ordertype_flag = "ORDER_TYPE";
    String ordertype_1 = "01";//电话预约
    String ordertype_2 = "02";//到店预约
    String ordertype_3 = "03";//在线预约

    String Y = "Y";
    String N = "N";

    String Z = "Z";//通过
    String T = "T";//作废
    //-------------时间-----------------
    String STARTTIME = "000000";
    String ENDTIME = "235959";
    String STARTTIME_POINT = "00:00:00";
    String ENDTIME_POINT = "23:59:59";
    //-----------
    String ST_MOENY_4 = "ST0004";
    //------
    String FLAG_X = "/";
    //--报表按钮顶级ID：因为特殊的上不展示需做特殊处理，排除掉
    String RPT_MENUID = "rp3b523e1cfd4c4c58a2b98b6cb17e4d";

    public static final String SORCE_CR = "CR0002";

    //旅支卡调拨核对状态
    String CHECK_STATUS = "LZ_CHECKSTATUS";
    //零调拨核对状态
    String CP_CHECK_STATUS = "CP_CHECKSTATUS";
    //旅支类型   美元卡和港币卡
    String KEY_LZ_TCTYPE = "LZ_TCTYPE";
    //旅支卡状态
    String KEY_LZ_TRIPCARDSTATUS = "LZ_TRIPCARDSTATUS";
    //旅支卡异常状态
    String KEY_LZ_TRADESTATUS = "LZ_TRADESTATUS";
    //旅支卡 兑换 异常处理状态
    String KEY_LZ_TRADEREVERSESTATUS = "LZ_TRADEREVERSESTATUS";
    //旅支卡异常处理状态
    String KEY_LZ_TRADEHANDLERSTATUS = "LZ_TRADEHANDLERSTATUS";
    //旅支卡充值支付状态
    String KEY_LZ_RECHARGE_PAY = "LZ_RECHARGE";
    //分隔符
    String SGN_SINGLE_QUOTES = "'";
    String SGN_COMMA = ",";
    //交易详情状态
    String LZ_ACOUNT_UPDATE_Y = "5";//账户更新成功
    //旅支卡状态
    public final static String LZ_TRIPCARDSTATUS_WAITACTIVE = "1";//待激活
    public final static String LZ_TRIPCARDSTATUS_REGULAR = "2";//正常使用
    public final static String LZ_TRIPCARDSTATUS_LOSTCARD = "3";//挂失
    public final static String LZ_TRIPCARDSTATUS_CHANGECARD = "4";//换卡

    static String LZ_TRADESTATUS11 = "11";    /*转账到银行卡成功*/
    static String LZ_TRADESTATUS12 = "12";    /*转账到银行卡失败*/
    static String LZ_TRADESTATUS13 = "13";    /*转账成功 旅支卡之间*/
    static String LZ_TRADESTATUS14 = "14";    /*转账失败 旅支卡之间*/
    static String LZ_TRADESTATUS15 = "15";    /*换卡成功 旅支卡之间*/
    static String LZ_TRADESTATUS16 = "16";    /*换卡失败 旅支卡之间*/
    static String LZ_TRADESTATUS17 = "17";    /*兑回成功*/
    static String LZ_TRADESTATUS18 = "18";    /*兑回失败*/


    static String HOT_AREA = "AREA";//币种管理区域类别

    static String IS_AIRPORTBRANCH = "AIRPORTBRANCHTYPE";//币种管理区域类别
    static int DCTOTALNUM = 2000;//报表导出条数

    String STOCK_MAID_BANK = "BANK";// 库存属性 BANK 账户

    String STOCK_MAID_CASH = "CASH";// 库存属性 CASH 现钞

    static String SHUPING = "28";
    static String HENPING = "10";


    /**
     * 冲正类型key
     */
    String KEY_REVERSE_TYPE = "REVERSE_TYPE";

    String KEY_MAKEUP_TYPE = "MAKEUP_TYPE";
    /**
     * 实时提交外管key
     */
    String KEY_COMMIT_SAFE = "COMMIT_SAFE";
    /**
     * 外管局提交状态
     */
    String WGJ_SUBMIT_STATUS = "WGJ_SUBMIT_STATUS";

    /****************************************提交外管相关常量**************START***************************/
    /**
     * 提交外管成功
     */
    String SB_SAFE_SUCCESS = "00";
    /**
     * 提交外管失败
     */
    String SB_SAFE_ERROR = "01";
    /**
     * 撤销失败
     */
    String SB_SAFE_CANCEL_ERROR = "02";
    /**
     * 撤销
     */
    String SB_SAFE_CANCEL = "03";
    /**
     * 待提交
     */
    String SB_SAFE_WAITE = "04";
    /**
     * 待冲正
     */
    String SB_SAFE_WAITECZ = "05";
    /**
     * 外管补录成功
     */
    String SB_SAFE_MU_SUCCESS = "06";
    /**
     * 本地撤销
     */
    String SB_SAFE_LOCAL_CANCEL = "07";
    /**
     * 手工补录
     */
    String SB_SAFE_MANUAL_MAKE = "08";
    /**
     * 手工撤销
     */
    String SB_SAFE_MANUAL_CANCEL = "09";

    /**
     * 补录原因：01-应急预案启动后补录
     */
    String SM_EMERGENCY_START = "01";

    /**
     * 补录原因：02-脱机操作
     */
    String SM_OFFLINE = "02";

    /**
     * 补录原因：03-差错业务撤销后补录
     */
    String SM_REVOKE = "03";

    /**
     * 补录原因：04-其他
     */
    String SM_OTHERS = "04";

    //----------------机构提交外管判断常量-----------------------------------------
    /**
     * 提交
     */
    String IS_COMMITSAFE_Y = "Y";
    /**
     * 不提交
     */
    String IS_COMMITSAFE_N = "N";

    String BRANCH_TYPE = "BRANCH_TYPE";//网点运营方式

    /**
     * 取图骗大小  以M为单位
     */
    String IMG_SIZE = "sys.img.size";
    /**
     * 取图路径
     */
    String IMG_PATH = "sys.img.path";
    String TEMP = "/TEMP";//临时图片根目录
    String ETCP = "ETCP";//真实图片跟目录
    String TEMP_ETCP = "/ETCP";//临时服务器路径


    //图片保存目录参数
    String IMAGES_UPLOAD_PATH = "/ETCP";
    String IMAGES_TEMP_PATH = "/TEMP";

    //交易业务类型--  加急业务/配钞库存 业务
    String EXGBUS_TYPE = "EXGBUS_TYPE";
    //全选网点
    String QXBCH = "QXBCH";
    //交易时间类型
    String EXGTIMEBUS_TYPE = "EXGTIMEBUS_TYPE";
    /**
     * 加急订单库存锁定
     */
    public static final String EXCHANGE_STOCK_LOCK_01 = "01";

    /**
     * 结售汇订单锁定
     */
    public static final String EXCHANGE_STOCK_LOCK_02 = "02";
    //exgIdList  订单流水重复
    public static final String EXGIDLIST = "EXGIDLIST";
    //initExgIdList  初始化订单流水重复
    public static final String INITEXGIDLIST = "INITEXGIDLIST";
    //SECONDRECEIPTID  订单 水单重复
    public static final String SECONDRECEIPTID = "SECONDRECEIPTID";

    public static final String STOCK_GROUP_TYPE = "STOCK_GROUP_TYPE";

}
