(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0dab5e"],{"6d82":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-card",[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.formInline}},[a("el-form-item",{attrs:{label:"审批人"}},[a("el-input",{attrs:{placeholder:"审批人"},model:{value:e.formInline.user,callback:function(t){e.$set(e.formInline,"user",t)},expression:"formInline.user"}})],1),a("el-form-item",{attrs:{label:"活动区域"}},[a("el-select",{attrs:{placeholder:"活动区域"},model:{value:e.formInline.region,callback:function(t){e.$set(e.formInline,"region",t)},expression:"formInline.region"}},[a("el-option",{attrs:{label:"区域一",value:"shanghai"}}),a("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("筛选")]),a("el-button",{attrs:{type:"primary",plain:""},on:{click:e.onSubmit}},[e._v("导出")]),a("el-button",{attrs:{type:"text"},on:{click:e.onSubmit}},[e._v("清空筛选")])],1)],1),a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"登录日志",name:"first"}},[[a("el-table",{staticStyle:{width:"100%"},attrs:{"default-sort":{prop:"date",order:"descending"},"header-cell-style":{background:"#f0f5ff"},data:e.tableData}},[a("el-table-column",{attrs:{sortable:"",prop:"date",label:"日期",width:"180"}}),a("el-table-column",{attrs:{prop:"name",label:"姓名",width:"180"}}),a("el-table-column",{attrs:{prop:"address",label:"地址"}})],1)]],2),a("el-tab-pane",{attrs:{label:"操作日志",name:"second"}},[[a("el-table",{staticStyle:{width:"100%"},attrs:{"default-sort":{prop:"date",order:"descending"},"header-cell-style":{background:"#f0f5ff"},data:e.tableData}},[a("el-table-column",{attrs:{sortable:"",prop:"date",label:"日期",width:"180"}}),a("el-table-column",{attrs:{prop:"name",label:"姓名",width:"180"}}),a("el-table-column",{attrs:{prop:"address",label:"地址"}})],1)]],2),a("el-tab-pane",{attrs:{label:"异常日志",name:"third"}},[[a("el-table",{staticStyle:{width:"100%"},attrs:{"default-sort":{prop:"date",order:"descending"},"header-cell-style":{background:"#f0f5ff"},data:e.tableData}},[a("el-table-column",{attrs:{sortable:"",prop:"date",label:"日期",width:"180"}}),a("el-table-column",{attrs:{prop:"name",label:"姓名",width:"180"}}),a("el-table-column",{attrs:{prop:"address",label:"地址"}})],1)]],2)],1),a("el-pagination",{attrs:{"current-page":1,"page-sizes":[3,5,10,15],"page-size":5,layout:"total, sizes, prev, pager, next, jumper",total:400},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)},n=[],r={data:function(){return{activeName:"first",formInline:{user:"",region:""},tableData:[{date:"2016-05-02",name:"王小虎",address:"上海市普陀区金沙江路 1518 弄"},{date:"2016-05-04",name:"王小虎",address:"上海市普陀区金沙江路 1517 弄"},{date:"2016-05-01",name:"王小虎",address:"上海市普陀区金沙江路 1519 弄"},{date:"2016-05-03",name:"王小虎",address:"上海市普陀区金沙江路 1516 弄"}]}},methods:{onSubmit:function(){},handleClick:function(e,t){},handleSizeChange:function(e){},handleCurrentChange:function(e){}}},o=r,s=a("2877"),i=Object(s["a"])(o,l,n,!1,null,"7b9285e0",null);t["default"]=i.exports}}]);
//# sourceMappingURL=chunk-2d0dab5e.272eabb7.js.map