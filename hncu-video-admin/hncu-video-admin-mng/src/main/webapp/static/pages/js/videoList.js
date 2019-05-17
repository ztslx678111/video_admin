
// 定义视频列表对象
var VideosList = function () {
	
    // 视频列表
	var handleVideosList = function() {
    	
		// 上下文对象路径
		var hdnContextPath = $("#hdnContextPath").val();
		var apiServer = $("#apiServer").val();
		
		var jqGrid = $("#videoList");  
        jqGrid.jqGrid({  
            caption: "所有视频列表",  
            url: hdnContextPath + "/video/videoList.action",  
            mtype: "post",  
            styleUI: 'Bootstrap',//设置jqgrid的全局样式为bootstrap样式  
            datatype: "json",  
            colNames: ['ID', '封面','视频内容', '观看地址', '时间长度(秒)', '长','宽','长宽比例','获赞数', '背景音乐', '发布者', '状态', '发布日期'],  
            colModel: [  
                { name: 'id', index: 'id', width: 30 },  
                { name: 'coverPath', index: 'coverPath', width: 50,
                	formatter:function(cellvalue, options, rowObject) {
                		var src = apiServer + cellvalue;
                		var img = "<img src='" + src + "' width='120'></img>"
			    		return img;
			    	}  
                },
                { name: 'videoDesc', index: 'videoDesc', width: 60 },
                { name: 'videoPath', index: 'videoPath', width: 30,
                	formatter:function(cellvalue, options, rowObject) {
                		var src = apiServer + cellvalue;
                		var display = "<a href='" + src + "' target='_blank'>点我播放</a>"
			    		return display;
			    	}
                },
                { name: 'videoSeconds', videoSeconds: 'videoWidth', width: 20 },
                { name: 'videoWidth', index: 'videoWidth', width: 10, hidden:true },
                { name: 'videoHeight', index: 'videoHeight', width: 10, hidden:true },
                { name: '', index: '', width: 30,
                	formatter:function(cellvalue, options, rowObject) {
                		var display = rowObject.videoWidth + "X" + rowObject.videoHeight;
			    		return display;
			    	}
                },
                { name: 'likeCounts', index: 'likeCounts', width: 25 },
                { name: 'bgmName', index: 'bgmName', width: 35 },
                { name: 'username', index: 'username', width: 15 },
                { name: 'status', index: 'status', width: 10,
                	formatter:function(cellvalue, options, rowObject) {
                		
                		var show = "";
                		if (cellvalue === 1) {
                			show = "发布中";
                		} else if (cellvalue === 2) {
                			show = "禁播";
                		}
			    		return show;
			    	}	
                },
                { name: 'createTime', index: 'createTime', width: 30, hidden: false,
                	formatter:function(cellvalue, options, rowObject) {
                		var createTime = Common.formatTime(cellvalue,'yyyy-MM-dd HH:mm:ss');
			    		return createTime;
			    	}
			    }
            ],  
            viewrecords: true,  		// 定义是否要显示总记录数
            rowNum: 10,					// 在grid上显示记录条数，这个参数是要被传递到后台
            rownumbers: true,  			// 如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'
            autowidth: true,  			// 如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
            height: 800,				// 表格高度，可以是数字，像素值或者百分比
            rownumWidth: 36, 			// 如果rownumbers为true，则可以设置行号 的宽度
            pager: "#videoListPager",		// 分页控件的id  
            subGrid: false				// 是否启用子表格
        }).navGrid('#videoListPager', {
            edit: false,
            add: false,
            del: false,
            search: false
        });
        
  
        // 随着窗口的变化，设置jqgrid的宽度  
        $(window).bind('resize', function () {  
            var width = $('.videoList_wrapper').width()*0.99;  
            jqGrid.setGridWidth(width);  
        });  
        
        // 不显示水平滚动条
        jqGrid.closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        
        // 条件查询
        $("#searchVideoListButton").click(function(){
        	var searchVideoListForm = $("#searchVideoListForm");
        	jqGrid.jqGrid().setGridParam({ 
        		page: 1,
                url: hdnContextPath + "/video/videoList.action?" + searchVideoListForm.serialize(),
            }).trigger("reloadGrid");
        });
    }
    
    return {
        // 初始化各个函数及对象
        init: function () {
        	handleVideosList();
        }

    };

}();


jQuery(document).ready(function() {
	VideosList.init();
});