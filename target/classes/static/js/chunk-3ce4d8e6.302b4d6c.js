(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3ce4d8e6"],{"04f8":function(e,l,t){"use strict";t.r(l);var a=function(){var e=this,l=e.$createElement,t=e._self._c||l;return t("el-card",{staticClass:"container"},[t("el-tree",{attrs:{data:e.data,props:e.defaultProps,accordion:""},on:{"node-click":e.handleNodeClick}}),t("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.formInline}},[t("el-form-item",{attrs:{label:"审批人"}},[t("el-input",{attrs:{placeholder:"审批人"},model:{value:e.formInline.user,callback:function(l){e.$set(e.formInline,"user",l)},expression:"formInline.user"}})],1),t("el-form-item",{attrs:{label:"活动区域"}},[t("el-select",{attrs:{placeholder:"活动区域"},model:{value:e.formInline.region,callback:function(l){e.$set(e.formInline,"region",l)},expression:"formInline.region"}},[t("el-option",{attrs:{label:"区域一",value:"shanghai"}}),t("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("查询")]),t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("新增")])],1)],1),t("el-table",{staticStyle:{width:"100%"},attrs:{"header-cell-style":{background:"#f0f5ff"},data:e.tableData,"default-sort":{prop:"date",order:"descending"}}},[t("el-table-column",{attrs:{prop:"date",label:"日期",width:"180",sortable:""}}),t("el-table-column",{attrs:{prop:"name",label:"姓名",width:"180"}}),t("el-table-column",{attrs:{prop:"address",label:"地址"}}),t("el-table-column",{attrs:{label:"状态操作"}},[[t("el-switch",{attrs:{"active-color":"#2f54eb","inactive-color":"#ff4949"},model:{value:e.value,callback:function(l){e.value=l},expression:"value"}})]],2),t("el-table-column",{attrs:{label:"操作"}},[[t("el-button",{attrs:{type:"text"}},[e._v("编辑")]),t("el-button",{staticStyle:{color:"red"},attrs:{type:"text"}},[e._v("删除")]),t("el-button",{attrs:{type:"text"}},[e._v("更多")])]],2)],1)],1)},n=[],r=(t("cadf"),t("551c"),t("f751"),t("097d"),{data:function(){return{data:[{label:"一级 1",children:[{label:"二级 1-1",children:[{label:"三级 1-1-1"}]}]},{label:"一级 2",children:[{label:"二级 2-1",children:[{label:"三级 2-1-1"}]},{label:"二级 2-2",children:[{label:"三级 2-2-1"}]}]},{label:"一级 3",children:[{label:"二级 3-1",children:[{label:"三级 3-1-1"}]},{label:"二级 3-2",children:[{label:"三级 3-2-1"}]}]}],defaultProps:{children:"children",label:"label"},formInline:{user:"",region:""},tableData:[{date:"2016-05-02",name:"王小虎",address:"上海市普陀区金沙江路 1518 弄"},{date:"2016-05-04",name:"王小虎",address:"上海市普陀区金沙江路 1517 弄"},{date:"2016-05-01",name:"王小虎",address:"上海市普陀区金沙江路 1519 弄"},{date:"2016-05-03",name:"王小虎",address:"上海市普陀区金沙江路 1516 弄"}],value:!0}},methods:{handleNodeClick:function(e){},onSubmit:function(){}}}),o=r,i=(t("c91c"),t("2877")),c=Object(i["a"])(o,a,n,!1,null,"7f173770",null);l["default"]=c.exports},8663:function(e,l,t){},c91c:function(e,l,t){"use strict";var a=t("8663"),n=t.n(a);n.a}}]);
//# sourceMappingURL=chunk-3ce4d8e6.302b4d6c.js.map