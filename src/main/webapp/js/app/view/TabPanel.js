/*
 * File: app/view/TabPanel.js
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

Ext.define('Feed.view.TabPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.tabpanel',

    requires: [
        'Feed.view.ShoppingCarList',
        'Feed.view.NetOrderList',
        'Feed.view.AddFormView',
        'Ext.navigation.View',
        'Ext.dataview.List',
        'Ext.navigation.Bar',
        'Ext.Button',
        'Ext.form.Panel',
        'Ext.form.FieldSet',
        'Ext.field.Select',
        'Ext.field.Number',
        'Ext.Spacer',
        'Ext.tab.Bar'
    ],

    config: {
        id:'TabPanelId',

        items: [
            {
                xtype: 'container',
                title: '网上订单',
                iconCls: 'home',
                id: 'netOrdersTab',
                layout: 'fit',
                items: [
                    {
                        xtype: 'navigationview',
                        id: 'netOrdersNav',
                        useTitleForBackButtonText: true,
                        items: [
                            {
                                xtype: 'NetOrderList',
                                title: '我的订单'
                            }
                        ],
                        navigationBar: {
                            items: [
                                {
                                    xtype: 'button',
                                    align: 'right',
                                    id: 'refreshBtn',
                                    ui: 'action',
                                    iconCls: 'refresh',
                                    text: '',
                                    handler:function(){
                                        Ext.getStore("NetOrderStoreId").loadList();
                                    }
                                }
                            ]
                        }
                    }
                ]
            },
            {
                xtype: 'container',
                title: '购物车',
                iconCls: 'compose',
                id: 'shoppingCarTab',
                layout: 'fit',
                items: [
                    {
                        xtype: 'navigationview',
                        id: 'shoppingCarNav',
                        useTitleForBackButtonText: true,
                        items: [
                            {
                                xtype: 'ShoppingCarList',
                                title: '我的购物车'
                            }
                        ],
                        navigationBar: {
                            items: [
                                {
                                    xtype: 'button',
                                    align: 'right',
                                    id: 'submitCarsBtn',
                                    ui: 'action',
                                    iconCls: 'download',
                                    text: '',
                                    handler:function(){
                                      Ext.getStore("ShoppingCarStoreId").loadCarItems();
                                    }
                                }
                            ]
                        }
                    }
                ]
            },
            {
                xtype: 'container',
                title: '订货中心',
                iconCls: 'add',
                id: 'addTab',
                layout: 'fit',
                items: [
                    {
                        xtype: 'navigationview',
                        id: 'addCarNav',
                        useTitleForBackButtonText: true,
                        items: [
                            {
                                xtype: 'AddFormView',
                                id: 'AddFormViewId',
                                title: '添加商品'
                            }
                        ]
                        //,
                        //navigationBar: {
                        //    items: [
                        //        {
                        //            xtype: 'button',
                        //            align: 'right',
                        //            id: 'addCarNewBtn',
                        //            ui: 'action',
                        //            iconCls: 'refresh',
                        //            text: '',
                        //            handler: function () {
                        //                Ext.getStore("ItemsId").loadCarItems();
                        //            }
                        //        }
                        //    ]
                        //}
                    }
                ]
            }
        ],
        tabBar: {
            docked: 'bottom'
        }
    }

});