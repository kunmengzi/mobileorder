Ext.define('Feed.controller.AddFormController', {
    extend: 'Ext.app.Controller',
    alias: 'controller.AddFormController',

    config: {
        models: [
            'ShoppingCarModel',
            "Feed.model.FormValidatorModel"
        ],
        stores: [
            'AddFormStore'
        ],
        views: [
            'AddFormView'
        ],

        refs: {
            materialTypeSelect:"#materialTypeSelectId",
            materialSelect:"#materialSelectId",
            measureUnitSelect:"#measureUnitSelectId",
            priceField:'#priceFieldId',
            clearBtn:'#clearBtnId',
            numFieldId:'#numFieldId',
            addCarBtn:'#addCarBtnId',
            addFormPanel:'#AddFormViewId'
        },

        control: {
            'materialTypeSelect':{
                change:'materialTypeChange'
            },
            'materialSelect':{
                change:'materialChange'
            },
            'measureUnitSelect':{
                change:'measureUnitChange'
            },
            'clearBtn':{
                tap:'clearAction'
            },
            'addCarBtn':{
                tap:'addCar'
            },
            'numFieldId':{
                change:'numChange'
            }
        }
    },

    numChange:function(comp,newVal,oldVal,opts) {
        if(!newVal && newVal<=0){
            Ext.getCmp("amountFieldId").setValue(0.00);
            return false;
        }

        var price = Ext.getCmp("priceFieldId").getValue();
        if(price){
            Ext.getCmp("amountFieldId").setValue(price * newVal);
        }
    },

    addCar:function(comp, e, eOpts){
        //校验
        var formData = Ext.getCmp("AddFormViewId").getValues(false,true);
        var formValidatorModel = Ext.create("Feed.model.FormValidatorModel",formData);
        var errors = formValidatorModel.validate();
        if(!errors.isValid()) {
            var errorMsg = "";
            errors.each(function (item, index, length) {
                errorMsg +=  item.getMessage() + '<br/>';
            });
            Ext.Msg.alert("数据不完整", errorMsg);
            return false;
        }

        Ext.Msg.confirm("确认操作", "确认添加到购物车吗？", function(buttonId,value,opt){
            if(buttonId=="no"){
                return false;
            }

            Ext.Ajax.request({
                url: CfgConst.addCarUrl,
                params: Ext.urlEncode(formData),
                method:'post',
                success: function(response){
                    var result = Ext.JSON.decode(response.responseText);
                    if(result && result.success){
                        Ext.Msg.alert("处理成功",result.message);
                        Ext.getStore("ShoppingCarStoreId").loadCarItems();
                        return true;
                    }else{
                        Ext.Msg.alert("处理失败",result.message);
                        return false;
                    }
                }
            });
        });
    },

    clearAction:function(comp, e, eOpts){
        this.getAddFormPanel().reset();
    },

    measureUnitChange:function(comp,newVal,oldVal,opts){
        if(!newVal || newVal=="0"){
            return false;
        }

        var materialId = Ext.getCmp("materialSelectId").getValue();
        Ext.Ajax.request({
            url: CfgConst.calPriceUrl,
            params: {
                materialId: materialId,
                unitId: newVal
            },
            success: function(response){
                var price = Ext.JSON.decode(response.responseText).price;
                Ext.getCmp("priceFieldId").setValue(price);
                var qty = Ext.getCmp("numFieldId").getValue();
                if(qty){
                    Ext.getCmp("amountFieldId").setValue(price * qty);
                }
            }
        });
    },

    materialChange:function(comp,newVal,oldVal,opts){
        if(!newVal || newVal=="0"){
            Ext.getStore("MeasureUnitStoreId").removeAll();
            return ;
        }

        Ext.getStore("MeasureUnitStoreId").loadMeasureUnit(newVal);
        //comp.getRecord().getData().code;
        this.getPriceField().setValue(comp.getRecord().getData().id);
    },

    materialTypeChange:function(comp,newVal,oldVal,opts){
        if(!newVal || newVal=="0"){
            Ext.getStore("MaterialStoreId").removeAll();
            return ;
        }

        Ext.getStore("MaterialStoreId").loadMaterials(newVal);
    },

    onItemsListActivate: function(newActiveItem, container, oldActiveItem, eOpts) {
        this.getApplication().fireEvent('updateNav');
    },

    launch: function() {
        Ext.getStore("MaterialGroupStoreId").load();
    },

    onUpdateNav: function() {
        var self = this;
    },

    init: function(application) {
        application.on([
        { event: 'updateNav', fn: this.onUpdateNav, scope: this }
        ]);
    }
});
