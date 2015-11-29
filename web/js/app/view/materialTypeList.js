/*
 * File: app/view/materialTypeList.js
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

Ext.define('Feed.view.materialTypeList', {
    extend: 'Ext.dataview.List',
    alias: 'widget.materialTypeList',

    requires: [
        'Ext.XTemplate'
    ],

    config: {
        itemTpl: [
            '<div>名称：{name}<br>',
            'code:{code}<br>',
            '    <input type="hidden" value="{id}"/>',
            '</div>'
        ]
    }

});