(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-03901d39"],{"3c9c":function(e,t,a){"use strict";var l=a("6c63"),r=a.n(l);r.a},5687:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-card",[a("el-row",[a("el-col",{attrs:{span:1}},[a("div",{staticClass:"top",on:{click:e.goback}},[a("i",{staticClass:"el-icon-back",staticStyle:{"font-size":"20px"}}),a("span",{staticStyle:{"margin-left":"5px"}},[e._v("返回")])])]),a("el-col",{attrs:{span:2}},[e._v("| 采购订单审核")])],1)],1),a("el-card",{staticStyle:{"margin-top":"20px"}},[a("div",{staticClass:"meta"},[e._v("申请采购单")]),a("el-form",{ref:"form",attrs:{model:e.form,"label-position":"top"}},[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"供应商名称"}},[a("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"负责人"}},[a("el-select",{attrs:{placeholder:"请选择活动区域"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}},[a("el-option",{attrs:{label:"区域一",value:"shanghai"}}),a("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"采购类型"}},[a("el-select",{attrs:{placeholder:"请选择活动区域"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}},[a("el-option",{attrs:{label:"区域一",value:"shanghai"}}),a("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"仓库"}},[a("el-select",{attrs:{placeholder:"请选择活动区域"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}},[a("el-option",{attrs:{label:"区域一",value:"shanghai"}}),a("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"开单日期"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"选择日期"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"交货日期"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"选择日期"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1)],1),a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"采购单号"}},[a("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"订单状态"}},[a("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"检验状态"}},[a("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"付款方式"}},[a("el-select",{attrs:{placeholder:"请选择活动区域"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}},[a("el-option",{attrs:{label:"区域一",value:"shanghai"}}),a("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",{attrs:{label:"备注"}},[a("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1)],1)],1)],1),a("el-card",{staticStyle:{"margin-top":"20px"}},[a("el-row",[a("el-col",{attrs:{span:4}},[a("div",{staticClass:"meta"},[e._v("采购条目")])])],1),a("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{"header-cell-style":{background:"#f0f5ff"},data:e.tableData}},[a("el-table-column",{attrs:{prop:"date",label:"日期",width:"180"}}),a("el-table-column",{attrs:{prop:"name",label:"姓名",width:"180"}}),a("el-table-column",{attrs:{prop:"address",label:"地址"}}),a("el-table-column",{attrs:{prop:"address",label:"地址"}}),a("el-table-column",{attrs:{label:"操作"}},[[a("el-button",{attrs:{type:"primary",size:"mini"}},[e._v("编辑")]),a("el-button",{attrs:{type:"danger",size:"mini"}},[e._v("删除")])]],2)],1),a("div",{staticClass:"footer"},[a("el-button",{attrs:{type:"primary"}},[e._v("打印单据")]),a("el-button",{attrs:{type:"info"}},[e._v("订单审核中")]),a("el-button",{attrs:{type:"danger"}},[e._v("撤回")]),a("el-button",{attrs:{type:"warning"}},[e._v("提交")])],1)],1)],1)},r=[],o={data:function(){return{form:{name:""},tableData:[{date:"2016-05-02",name:"王小虎",address:"上海市普陀区金沙江路 1518 弄"},{date:"2016-05-04",name:"王小虎",address:"上海市普陀区金沙江路 1517 弄"},{date:"2016-05-01",name:"王小虎",address:"上海市普陀区金沙江路 1519 弄"},{date:"2016-05-03",name:"王小虎",address:"上海市普陀区金沙江路 1516 弄"}]}},methods:{goback:function(){this.$router.go(-1)}}},s=o,n=(a("3c9c"),a("2877")),m=Object(n["a"])(s,l,r,!1,null,"43a50fc9",null);t["default"]=m.exports},"6c63":function(e,t,a){}}]);
//# sourceMappingURL=chunk-03901d39.a59c1d37.js.map