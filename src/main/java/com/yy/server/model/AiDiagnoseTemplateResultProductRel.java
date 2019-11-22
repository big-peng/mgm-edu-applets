package com.yy.server.model;

import javax.persistence.*;

@Table(name = "ai_diagnose_template_result_product_rel")
public class AiDiagnoseTemplateResultProductRel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 结果ID
     */
    @Column(name = "result_id")
    private Integer resultId;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 序号
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取结果ID
     *
     * @return result_id - 结果ID
     */
    public Integer getResultId() {
        return resultId;
    }

    /**
     * 设置结果ID
     *
     * @param resultId 结果ID
     */
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    /**
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取序号
     *
     * @return order_id - 序号
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置序号
     *
     * @param orderId 序号
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}