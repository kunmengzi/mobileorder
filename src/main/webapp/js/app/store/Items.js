/*
 * File: app/store/Items.js
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

Ext.define('Feed.store.Items', {
    extend: 'Ext.data.Store',
    alias: 'store.Items',

    requires: [
        'Feed.model.Item',
        'Ext.data.reader.Json'
    ],

    config: {
        model: 'Feed.model.Item',
        storeId: 'ItemsId',
        proxy: {
            type: 'ajax',
            url: 'http://localhost:8888/d/q/carItemList.json',
            reader: {
                type: 'json',
                rootProperty: 'data'
            }
        }
    },

    loadCarItems: function(itemUrl, numItems) {
        this.load({
            params:{
            //    q: itemUrl,
                num: numItems || 20
            }
        });

    }

});