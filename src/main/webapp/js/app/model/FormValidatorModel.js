Ext.define('Feed.model.FormValidatorModel', {
    extend: 'Ext.data.Model',
    alias: 'model.FormValidatorModel',
    requires: [
        'Ext.data.Field'
    ],

    config: {
        fields: [{
            name: 'price'
        }, {
            name: 'amount'
        }, {
            name: "qty"
        }, {
            name: "materialGroup"
        }, {
            name: "material"
        }, {
            name: "measureUnit"
        }
        ],
        validations: [
            {
                field: 'materialGroup',
                type: 'length',
                min: 2,
                message: '请选择类目'
            }, {
                field: 'material',
                type: 'length',
                min: 2,
                message: '请选择商品'
            }, {
                field: 'measureUnit',
                type: 'length',
                min: 2,
                message: '请选择单位'
            }, {
                field: 'qty',
                type: 'format',
                message: '请输入数量',
                matcher: /[1-9]\d*(\.\d+)?/
            }, {
                field: 'price',
                type: 'format',
                message: '价格为0不能下单',
                matcher: /[1-9]\d*(\.\d+)?/
            },
            {
                field: 'amount',
                type: 'format',
                message: '金额为0不能下单',
                matcher: /[1-9]\d*(\.\d+)?/
            }]
    }
});
