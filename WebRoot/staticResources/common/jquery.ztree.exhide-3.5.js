/*
 * JQuery zTree exHideNodes v3.5.19.2
 * http://zTree.me/
 *
 * Copyright (c) 2010 Hunter.z
 *
 * Licensed same as jquery - MIT License
 * http://www.opensource.org/licenses/mit-license.php
 *
 * email: hunter.z@263.net
 * Date: 2015-11-15
 */
(function(i){i.extend(!0,i.fn.zTree._z,{view:{clearOldFirstNode:function(c,a){for(var b=a.getNextNode();b;){if(b.isFirstNode){b.isFirstNode=!1;d.setNodeLineIcos(c,b);break}if(b.isLastNode)break;b=b.getNextNode()}},clearOldLastNode:function(c,a,b){for(a=a.getPreNode();a;){if(a.isLastNode){a.isLastNode=!1;b&&d.setNodeLineIcos(c,a);break}if(a.isFirstNode)break;a=a.getPreNode()}},makeDOMNodeMainBefore:function(c,a,b){c.push("<li ",b.isHidden?"style='display:none;' ":"","id='",b.tId,"' class='",l.className.LEVEL,
b.level,"' tabindex='0' hidefocus='true' treenode>")},showNode:function(c,a){a.isHidden=!1;f.initShowForExCheck(c,a);j(a,c).show()},showNodes:function(c,a,b){if(a&&a.length!=0){var e={},g,k;for(g=0,k=a.length;g<k;g++){var h=a[g];if(!e[h.parentTId]){var i=h.getParentNode();e[h.parentTId]=i===null?f.getRoot(c):h.getParentNode()}d.showNode(c,h,b)}for(var j in e)a=e[j][c.data.key.children],d.setFirstNodeForShow(c,a),d.setLastNodeForShow(c,a)}},hideNode:function(c,a){a.isHidden=!0;a.isFirstNode=!1;a.isLastNode=
!1;f.initHideForExCheck(c,a);d.cancelPreSelectedNode(c,a);j(a,c).hide()},hideNodes:function(c,a,b){if(a&&a.length!=0){var e={},g,k;for(g=0,k=a.length;g<k;g++){var h=a[g];if((h.isFirstNode||h.isLastNode)&&!e[h.parentTId]){var i=h.getParentNode();e[h.parentTId]=i===null?f.getRoot(c):h.getParentNode()}d.hideNode(c,h,b)}for(var j in e)a=e[j][c.data.key.children],d.setFirstNodeForHide(c,a),d.setLastNodeForHide(c,a)}},setFirstNode:function(c,a){var b=c.data.key.children,e=a[b].length;e>0&&!a[b][0].isHidden?
a[b][0].isFirstNode=!0:e>0&&d.setFirstNodeForHide(c,a[b])},setLastNode:function(c,a){var b=c.data.key.children,e=a[b].length;e>0&&!a[b][0].isHidden?a[b][e-1].isLastNode=!0:e>0&&d.setLastNodeForHide(c,a[b])},setFirstNodeForHide:function(c,a){var b,e,g;for(e=0,g=a.length;e<g;e++){b=a[e];if(b.isFirstNode)break;if(!b.isHidden&&!b.isFirstNode){b.isFirstNode=!0;d.setNodeLineIcos(c,b);break}else b=null}return b},setFirstNodeForShow:function(c,a){var b,e,g,f,h;for(e=0,g=a.length;e<g;e++)if(b=a[e],!f&&!b.isHidden&&
b.isFirstNode){f=b;break}else if(!f&&!b.isHidden&&!b.isFirstNode)b.isFirstNode=!0,f=b,d.setNodeLineIcos(c,b);else if(f&&b.isFirstNode){b.isFirstNode=!1;h=b;d.setNodeLineIcos(c,b);break}return{"new":f,old:h}},setLastNodeForHide:function(c,a){var b,e;for(e=a.length-1;e>=0;e--){b=a[e];if(b.isLastNode)break;if(!b.isHidden&&!b.isLastNode){b.isLastNode=!0;d.setNodeLineIcos(c,b);break}else b=null}return b},setLastNodeForShow:function(c,a){var b,e,g,f;for(e=a.length-1;e>=0;e--)if(b=a[e],!g&&!b.isHidden&&
b.isLastNode){g=b;break}else if(!g&&!b.isHidden&&!b.isLastNode)b.isLastNode=!0,g=b,d.setNodeLineIcos(c,b);else if(g&&b.isLastNode){b.isLastNode=!1;f=b;d.setNodeLineIcos(c,b);break}return{"new":g,old:f}}},data:{initHideForExCheck:function(c,a){if(a.isHidden&&c.check&&c.check.enable){if(typeof a._nocheck=="undefined")a._nocheck=!!a.nocheck,a.nocheck=!0;a.check_Child_State=-1;d.repairParentChkClassWithSelf&&d.repairParentChkClassWithSelf(c,a)}},initShowForExCheck:function(c,a){if(!a.isHidden&&c.check&&
c.check.enable){if(typeof a._nocheck!="undefined")a.nocheck=a._nocheck,delete a._nocheck;if(d.setChkClass){var b=j(a,l.id.CHECK,c);d.setChkClass(c,b,a)}d.repairParentChkClassWithSelf&&d.repairParentChkClassWithSelf(c,a)}}}});var i=i.fn.zTree,m=i._z.tools,l=i.consts,d=i._z.view,f=i._z.data,j=m.$;f.addInitNode(function(c,a,b){if(typeof b.isHidden=="string")b.isHidden=m.eqs(b.isHidden,"true");b.isHidden=!!b.isHidden;f.initHideForExCheck(c,b)});f.addBeforeA(function(){});f.addZTreeTools(function(c,a){a.showNodes=
function(a,b){d.showNodes(c,a,b)};a.showNode=function(a,b){a&&d.showNodes(c,[a],b)};a.hideNodes=function(a,b){d.hideNodes(c,a,b)};a.hideNode=function(a,b){a&&d.hideNodes(c,[a],b)};var b=a.checkNode;if(b)a.checkNode=function(c,d,f,h){(!c||!c.isHidden)&&b.apply(a,arguments)}});var n=f.initNode;f.initNode=function(c,a,b,e,g,i,h){var j=(e?e:f.getRoot(c))[c.data.key.children];f.tmpHideFirstNode=d.setFirstNodeForHide(c,j);f.tmpHideLastNode=d.setLastNodeForHide(c,j);h&&(d.setNodeLineIcos(c,f.tmpHideFirstNode),
d.setNodeLineIcos(c,f.tmpHideLastNode));g=f.tmpHideFirstNode===b;i=f.tmpHideLastNode===b;n&&n.apply(f,arguments);h&&i&&d.clearOldLastNode(c,b,h)};var o=f.makeChkFlag;if(o)f.makeChkFlag=function(c,a){(!a||!a.isHidden)&&o.apply(f,arguments)};var p=f.getTreeCheckedNodes;if(p)f.getTreeCheckedNodes=function(c,a,b,e){if(a&&a.length>0){var d=a[0].getParentNode();if(d&&d.isHidden)return[]}return p.apply(f,arguments)};var q=f.getTreeChangeCheckedNodes;if(q)f.getTreeChangeCheckedNodes=function(c,a,b){if(a&&
a.length>0){var d=a[0].getParentNode();if(d&&d.isHidden)return[]}return q.apply(f,arguments)};var r=d.expandCollapseSonNode;if(r)d.expandCollapseSonNode=function(c,a,b,e,f){(!a||!a.isHidden)&&r.apply(d,arguments)};var s=d.setSonNodeCheckBox;if(s)d.setSonNodeCheckBox=function(c,a,b,e){(!a||!a.isHidden)&&s.apply(d,arguments)};var t=d.repairParentChkClassWithSelf;if(t)d.repairParentChkClassWithSelf=function(c,a){(!a||!a.isHidden)&&t.apply(d,arguments)}})(jQuery);