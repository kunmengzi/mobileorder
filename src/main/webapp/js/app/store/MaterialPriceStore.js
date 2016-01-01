/*
 * File: app/store/Material.js
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

Ext.define('Feed.store.MaterialPriceStore', {
    extend: 'Ext.data.Store',
    alias: 'store.MaterialPriceStore',

    requires: [
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    config: {
        storeId: 'MaterialPriceStoreId',
        proxy: {
            type: 'ajax',
            url: CfgConst.calPriceUrl,
            reader: {
                type: 'json',
                rootProperty: 'data'
            }
        }
    },

    calPrice: function(materialId, unitId) {
        this.load({
            params:{
                materialId: materialId,
                unitId: unitId
            },
            callback: function(records, operation, success) {
                console.log(records);

                var price = records.price;
                Ext.getCmp("priceFieldId").setValue(price);
                var qty = Ext.getCmp("numFieldId").getValue();
                Ext.getCmp("amountFieldId").setValue(price*qty);
            }
        });
    }
});
