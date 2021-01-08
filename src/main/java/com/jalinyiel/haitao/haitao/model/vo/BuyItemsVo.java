package com.jalinyiel.haitao.haitao.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BuyItemsVo implements Serializable{

    private static final long serialVersionUID = -5784579624694076092L;

    /**
     * 购买的商品
     */
    private List<BuyItem> buyItems;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 收件人昵称
     */
    private String receiver;


    @Data
    public class BuyItem implements Serializable {

        private Long itemId;

        private String itemName;

        private Long actualPrice;

        private Integer amount;

        private String itemImage;
    }
}
