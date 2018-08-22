package com.code.repository.util.test;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * DO�Ļ���
 * <ul>
 * creator,modifier,isDeleted ����ֶηǱ�������
 * </ul>
 */
public class BaseDO implements Serializable {

    /**   **/
    private static final long serialVersionUID = 799684506369425521L;
    /**
     * ��ɾ��
     */
    public static final Integer UNDELETE = Integer.valueOf(0);
    /**
     * ɾ��״̬
     */
    public static final Integer DELETED = Integer.valueOf(1);

    private Long id;

    /**
     * ������
     */
    private String creator;
    /**
     * �޸���
     */
    private String modifier;
    /**
     * ����ʱ��
     */
    private Date gmtCreate;
    /**
     * �޸�ʱ��
     */
    private Date gmtModified;

    /**
     * �Ƿ���ɾ��,0:δɾ�� 1:��ɾ��
     */
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
