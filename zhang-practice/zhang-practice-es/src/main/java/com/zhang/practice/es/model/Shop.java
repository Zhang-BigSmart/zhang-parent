//package com.zhang.practice.es.model;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.DateFormat;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
///**
// * @ClassName Shop
// * @Description:
// * @Author: zhangzh
// * @Date 2019/4/30 15:42
// */
//@Data
//@Document(indexName = "mc",type = "mc_shop")
//public class Shop implements Serializable {
//
//    @Id
//    private Long id;
//
//    /**
//     * 美团店铺ID
//     */
//    @Field(type = FieldType.Long)
//    private Long shopId;
//
//    /**
//     * 商家名称
//     */
//    @Field(type = FieldType.Text, analyzer = "ik_smart")
//    private String shopName;
//
//    /**
//     * 商家地址
//     */
//    @Field(type = FieldType.Keyword, index = false)
//    private String addr;
//
//    /**
//     * 商家logo
//     */
//    @Field(type = FieldType.Keyword, index = false)
//    private String shopLogo;
//
//    /**
//     * 商家url
//     */
//    @Field(type = FieldType.Keyword, index = false)
//    private String shopUrl;
//
//    /**
//     * 电话
//     */
//    @Field(type = FieldType.Keyword, index = false)
//    private String tel;
//
//    /**
//     * 创建时间
//     */
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private LocalDateTime createTime;
//
//    /**
//     * 更新时间
//     */
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private LocalDateTime updateTime;
//
//    @Field(type = FieldType.Integer)
//    private Integer isShow;
//
//    @Field(type = FieldType.Integer)
//    private Integer provinceId;
//
//    @Field(type = FieldType.Integer)
//    private Integer cityId;
//
//    @Field(type = FieldType.Integer)
//    private Integer areaId;
//
//    @Field(type = FieldType.Integer)
//    private Integer typeId;
//
//    @Field(type = FieldType.Integer)
//    private Integer regionId;
//
//    @Field(type = FieldType.Integer)
//    private Integer zoneId;
//
//    @Field(type = FieldType.Double)
//    private Double perCost;
//
//    @Field(type = FieldType.Keyword)
//    private String commentNum;
//
//    @Field(type = FieldType.Keyword)
//    private String businessHours;
//
//    @Field(type = FieldType.Integer)
//    private Integer seq;
//
//    @Field(type = FieldType.Object)
//    private Double[] location;
//
//    @Field(type = FieldType.Double)
//    private Double score;
//
//    @Field(type = FieldType.Double, index = false)
//    private String remark;
//
//    @Override
//    public String toString() {
//        return "Shop{" +
//                "id=" + id +
//                ", shopId=" + shopId +
//                ", shopName='" + shopName + '\'' +
//                ", addr='" + addr + '\'' +
//                ", shopLogo='" + shopLogo + '\'' +
//                ", shopUrl='" + shopUrl + '\'' +
//                ", tel='" + tel + '\'' +
//                ", createTime=" + createTime +
//                ", updateTime=" + updateTime +
//                ", isShow=" + isShow +
//                ", provinceId=" + provinceId +
//                ", cityId=" + cityId +
//                ", areaId=" + areaId +
//                ", typeId=" + typeId +
//                ", regionId=" + regionId +
//                ", zoneId=" + zoneId +
//                ", perCost=" + perCost +
//                ", commentNum='" + commentNum + '\'' +
//                ", businessHours='" + businessHours + '\'' +
//                ", seq=" + seq +
//                ", location=" + Arrays.toString(location) +
//                ", score=" + score +
//                ", remark='" + remark + '\'' +
//                '}';
//    }
//}
