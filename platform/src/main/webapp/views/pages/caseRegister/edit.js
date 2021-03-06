(function () {
    var EditModule = smart.SingleEditModule.extend({

        //构造函数
        init: function (options) {
            smart.SingleEditModule.fn.init.call(this, options);
        },

        //初始化组件
        initComponents: function () {
            smart.kendoui.datePicker(this.$("#birthday"));

            smart.kendoui.comboBox(this.$("#gender"), {
                dataSource: smart.Enums["com.bycc.enumitem.Gender"].getData()
            });

            smart.kendoui.comboBox(this.$("#certificateType"), {
                dataSource: smart.Enums["com.bycc.enumitem.CertificateType"].getData()
            });
        },

        //overwrite
        getModelData: function () {
            var caseRecordModule = smart.Module.getModule("SmartCaseRecordIndex"),
                caseModule = smart.Module.getModule("SmartCaseIndex");

            var data = smart.SingleEditModule.fn.getModelData.call(this);
            data.enterReason = this.$(":radio[name='enterReason']:checked").val();

            //【案件】和【办案记录】页面点击"开始办案"时传不同参数
            if (caseRecordModule) {
                data.caseRecordId = caseRecordModule.getSelectItem().id;
            }
            if (caseModule) {
                data.caseId = caseModule.getSelectItem().id;
            }

            if(this.viewModel.casePeople.photo) {
                data.photo = this.viewModel.casePeople.photo;
            }

            return data;
        },

        //绑定事件
        bindEvents: function () {
            //调用父类方法绑定增删改查
            smart.SingleEditModule.fn.bindEvents.call(this);

            smart.bind('#' + this.containerId + ' #btnDoRead', [this.readIdCard, this]);
        },

        readIdCard: function(){
            var me = this;
            $.ajax({
                url: "http://localhost:8088",
                dataType: "jsonp",
                jsonpCallback:'parse',
                method :'GET',
                success: function (json) {
                    var idcard = JSON.parse(json);
                    me.viewModel.set("casePeople",{
                        name: idcard.name,
                        birthday: idcard.birthday,
                        gender: idcard.gender,
                        address: idcard.address,
                        certificateType: "ID",
                        certificateNum: idcard.id,
                        photo: 'data:image/bmp;base64,'+ idcard.photo
                    });
                }
            });
        }
    });

    new EditModule({
        name: "SmartCaseRegisterEdit", //必需，Edit模块名
        containerId: "ctnCaseRegisterEditWrap", //必需，Edit模块的容器id
        restUrl: "/caseRegister/", //必需，请求的rest地址
        modelName: 'casePeople', //必需，model名
        model: {//必需，model用于MVVM
            casePeople: {
                id: "",
                name: "",
                birthday: "",
                gender: "",
                telNum: "",
                certificateType: "",
                certificateNum: "",
                address: "",
                enterReason: "",
                otherEnterReason: "",
                photo:""
            }
        },
        eventPrefix: "REGISTER"
    });
})();
