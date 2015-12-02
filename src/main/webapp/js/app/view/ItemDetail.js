/*
 * File: app/view/itemDetail.js
 *
 * This file was generated by Sencha Architect version 3.2.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Sencha Touch 2.4.x library, under independent license.
 * License of Sencha Architect does not include license for Sencha Touch 2.4.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Feed.view.ItemDetail', {
    extend: 'Ext.form.Panel',
    alias: 'widget.ItemDetail',

    requires: [
        'Ext.form.FieldSet',
        'Ext.field.Text',
        'Ext.Spacer',
        'Ext.Button'
    ],

    config: {
        items: [
            {
                xtype: 'fieldset',
                title: '购物车',
                items: [
                    {
                        xtype: 'textfield',
                        label: '类别',
                        readOnly: true
                    },
                    {
                        xtype: 'textfield',
                        label: '商品',
                        readOnly: true
                    },
                    {
                        xtype: 'textfield',
                        label: '单位',
                        readOnly: true
                    },
                    {
                        xtype: 'textfield',
                        label: '数量',
                        readOnly: true
                    },
                    {
                        xtype: 'textfield',
                        label: '单价',
                        readOnly: true
                    },
                    {
                        xtype: 'textfield',
                        label: '金额',
                        readOnly: true
                    },
                    {
                        xtype: 'textfield',
                        label: '时间',
                        readOnly: true
                    }
                ]
            },
            {
                xtype: 'container',
                layout: 'hbox',
                items: [
                    {
                        xtype: 'spacer'
                    },
                    {
                        xtype: 'button',
                        ui: 'decline',
                        icon: 'true',
                        iconCls: 'delete',
                        text: '删除'
                    },
                    {
                        xtype: 'spacer'
                    },
                    {
                        xtype: 'button',
                        ui: 'confirm',
                        icon: 'true',
                        iconCls: 'download',
                        text: '确认下单'
                    },
                    {
                        xtype: 'spacer'
                    }
                ]
            }
        ]
    }

});