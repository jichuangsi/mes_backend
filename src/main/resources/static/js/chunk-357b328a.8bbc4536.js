(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-357b328a"],{4345:function(e,t,a){},4875:function(e,t,a){"use strict";var l=a("4345"),r=a.n(l);r.a},"589b":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-card",[a("el-tabs",{model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"生产计划审核",name:"first"}},[[a("el-form",{ref:"form",attrs:{"label-width":"80px"}},[a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"生产批号"}},[a("el-input")],1)],1),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"生产单号"}},[a("el-input")],1)],1),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"生产日期"}},[a("el-date-picker",{attrs:{type:"date",placeholder:"选择日期"},model:{value:e.value1,callback:function(t){e.value1=t},expression:"value1"}})],1)],1),a("el-col",{attrs:{span:6}},[a("el-button",{attrs:{type:"primary"}},[e._v("筛选")]),a("el-button",{attrs:{type:"primary"}},[e._v("导出")]),a("el-button",{attrs:{type:"text"}},[e._v("清空筛选")])],1)],1),a("el-table",{staticStyle:{width:"100%"},attrs:{"header-cell-style":{background:"#f0f5ff"},data:e.tableData}},[a("el-table-column",{attrs:{prop:"date",label:"日期"}}),a("el-table-column",{attrs:{prop:"name",label:"姓名"}}),a("el-table-column",{attrs:{prop:"address",label:"地址"}}),a("el-table-column",{attrs:{label:"操作"}},[[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.toDetail}},[e._v("查看")])]],2)],1)],1),a("el-pagination",{attrs:{"current-page":4,"page-sizes":[100,200,300,400],"page-size":100,layout:"total, sizes, prev, pager, next, jumper",total:400}})]],2),a("el-tab-pane",{attrs:{label:"审核设置",name:"second"}},[[a("el-form",{ref:"form",attrs:{"label-width":"80px"}},[a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"订单编号"}},[a("el-input")],1)],1),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"开单时间"}},[a("el-date-picker",{attrs:{type:"date",placeholder:"选择日期"},model:{value:e.value1,callback:function(t){e.value1=t},expression:"value1"}})],1)],1),a("el-col",{attrs:{span:6}},[a("span",{staticClass:"tips"},[e._v("*审核层级按数字由小到大排序，数字越小越先审核，如：审核层级为1的人先审核，然后按数字依次审核。")])]),a("el-col",{attrs:{span:6}},[a("el-button",{attrs:{type:"primary"}},[e._v("筛选")]),a("el-button",{attrs:{type:"primary",plain:""},on:{click:function(t){e.addDialogVisible=!0}}},[e._v("新增")])],1)],1),a("el-table",{staticStyle:{width:"100%"},attrs:{"header-cell-style":{background:"#f0f5ff"},data:e.tableData}},[a("el-table-column",{attrs:{prop:"date",label:"日期"}}),a("el-table-column",{attrs:{prop:"name",label:"姓名"}}),a("el-table-column",{attrs:{prop:"address",label:"地址"}}),a("el-table-column",{attrs:{label:"操作"}},[[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.toDetail}},[e._v("查看")])]],2)],1)],1),a("el-pagination",{attrs:{"current-page":4,"page-sizes":[100,200,300,400],"page-size":100,layout:"total, sizes, prev, pager, next, jumper",total:400}})]],2)],1),a("el-dialog",{attrs:{title:"提示",visible:e.addDialogVisible,width:"40%"},on:{"update:visible":function(t){e.addDialogVisible=t}}},[a("el-form",{ref:"form",staticStyle:{width:"500px"},attrs:{model:e.form,"label-width":"120px"}},[a("el-form-item",{attrs:{label:"层级名称"}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),a("el-form-item",{attrs:{label:"审核层级"}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),a("el-form-item",{attrs:{label:"审核人"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}},[a("el-option",{attrs:{label:"区域一",value:"shanghai"}}),a("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1),a("el-form-item",{attrs:{label:"审核人职称"}},[a("el-input",{attrs:{disabled:""},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.addDialogVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addDialogVisible=!1}}},[e._v("确 定")])],1)],1)],1)},r=[],s={data:function(){return{value1:"",form:{name:""},addDialogVisible:!1,activeName:"first",tableData:[{date:"2016-05-02",name:"王小虎",address:"上海市普陀区金沙江路 1518 弄"},{date:"2016-05-04",name:"王小虎",address:"上海市普陀区金沙江路 1517 弄"},{date:"2016-05-01",name:"王小虎",address:"上海市普陀区金沙江路 1519 弄"},{date:"2016-05-03",name:"王小虎",address:"上海市普陀区金沙江路 1516 弄"}]}},methods:{toggle:function(e){this.status=e},toDetail:function(){this.$router.push("/examine/detail")}}},o=s,i=(a("4875"),a("2877")),n=Object(i["a"])(o,l,r,!1,null,"8dc89f4e",null);t["default"]=n.exports}}]);
//# sourceMappingURL=chunk-357b328a.8bbc4536.js.map