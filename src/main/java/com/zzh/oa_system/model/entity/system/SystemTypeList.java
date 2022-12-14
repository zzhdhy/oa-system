package com.zzh.oa_system.model.entity.system;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 15:41
 * description:
 */
@Entity
@Table(name = "aoa_type_list")
public class SystemTypeList {

    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;            //类型id

    @Column(name = "type_name")
    @NotEmpty(message = "类型名称不能为空")
    private String typeName;        //类型名字

    @Column(name = "sort_value")
    private Integer typeSortValue;    //排序值

    @Column(name = "type_model")
    private String typeModel;        //所属模块

    @Column(name = "type_color")
    private String typeColor;        //类型颜色

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeSortValue() {
        return typeSortValue;
    }

    public void setTypeSortValue(Integer typeSortValue) {
        this.typeSortValue = typeSortValue;
    }

    public String getTypeModel() {
        return typeModel;
    }

    public void setTypeModel(String typeModel) {
        this.typeModel = typeModel;
    }

    public String getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(String typeColor) {
        this.typeColor = typeColor;
    }

    @Override
    public String toString() {
        return "SystemTypeList [typeId=" + typeId + ", typeName=" + typeName + ", typeSortValue=" + typeSortValue
                + ", typeModel=" + typeModel + ", typeColor=" + typeColor + "]";
    }


}
