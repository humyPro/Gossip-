//获取当前时间
function getNowFormatDate(day) {
	var date = new Date();
	//n代表天数,加号表示未来n天的此刻时间,减号表示过去n天的此刻时间
	var milliseconds = date.getTime() - 1000 * 60 * 60 * 24 * day;
	//getTime()方法返回Date对象的毫秒数,但是这个毫秒数不再是Date类型了,而是number类型,所以需要重新转换为Date对象,方便格式化
	var newDate = new Date(milliseconds);
	var seperator1 = ".";
	var strDate = newDate.getDate();
	var month = newDate.getMonth() + 1;
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = month + seperator1 + (strDate);
	return currentdate;
};
//用户活跃度
(function() {
	var myChart = echarts.init(document.getElementById("widget-chart-box-1"));
	var labelTop = {

		normal : {
			label : {
				show : true,
				position : 'center',
				formatter : '{b}',
				textStyle : {
					baseline : 'bottom'
				}
			},
			labelLine : {
				show : false
			}

		}
	};
	var labelFromatter = {
		normal : {
			label : {
				formatter : function(params) {
					return 100 - params.value + '%'
				},
				textStyle : {
					baseline : 'center'
				}
			}
		},
	}
	var labelBottom = {
		normal : {
			color : '#ccc',
			label : {
				show : true,
				position : 'center'
			},
			labelLine : {
				show : false
			}
		},
		emphasis : {
			color : '#ccc'
		}
	};
	var radius = [ 40, 35 ];
	$.ajax({
		url : "/yonghuhuoyuedu",
		type : "get",
		async : true,
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				var num1 = result.data;
				var num2 = 100 - num1;
				myChart
						.setOption({
							legend : {
								x : 'center',
								y : 'center',
							},
	
							grid : {
								x : 0,
								y : 0,
								x2 : 0,
								y2 : 0
							},
	
							toolbox : {
								show : true,
								feature : {
									magicType : {
										show : true,
										type : [ 'pie', 'funnel' ],
										option : {
											funnel : {
												width : '20%',
												height : '30%',
												itemStyle : {
													normal : {
														label : {
															formatter : function(
																	params) {
																return 'other\n'
																		+ params.value
																		+ '%\n'
															},
															textStyle : {
																baseline : 'middle'
															}
														}
													},
												}
											}
										}
									}
	
								}
							},
							series : [ {
								type : 'pie',
	
								radius : radius,
								x : '0%', // for funnel
								itemStyle : labelFromatter,
								data : [ {
									name : 'other',
									value : num2,
									itemStyle : labelBottom
								}, {
									name : '',
									value : num1,
									itemStyle : labelTop
								} ]
							} ]
						});
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
})();

//本月说说占比
(function() {
	var myChart2 = echarts.init(document.getElementById("widget-chart-box-2"));

	var labelTop = {

		normal : {
			label : {
				show : true,
				position : 'center',
				formatter : '{b}',
				textStyle : {
					baseline : 'bottom'
				}
			},
			labelLine : {
				show : false
			}

		}
	};
	var labelFromatter = {
		normal : {
			label : {
				formatter : function(params) {
					return 100 - params.value + '%'
				},
				textStyle : {
					baseline : 'center'
				}
			}
		},
	}
	var labelBottom = {
		normal : {
			color : '#ccc',
			label : {
				show : true,
				position : 'center'
			},
			labelLine : {
				show : false
			}
		},
		emphasis : {
			color : '#ccc'
		}
	};
	var radius = [ 40, 35 ];

	option = {

		legend : {
			x : 'center',
			y : 'center',
		},

		grid : {
			x : 0,
			y : 0,
			x2 : 0,
			y2 : 0
		},

		toolbox : {
			show : true,
			feature : {
				magicType : {
					show : true,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							width : '20%',
							height : '30%',
							itemStyle : {
								normal : {
									label : {
										formatter : function(params) {
											return 'other\n' + params.value
													+ '%\n'
										},
										textStyle : {
											baseline : 'middle'
										}
									}
								},
							}
						}
					}
				}

			}
		},
		series : [ {
			type : 'pie',

			radius : radius,
			x : '0%', // for funnel
			itemStyle : labelFromatter,
			data : [ {
				name : 'other',
				value : [],
				itemStyle : labelBottom
			}, {
				name : '',
				value : [],
				itemStyle : labelTop
			} ]
		} ],
		animation : true
	};

	$.ajax({
		url : "/benyueshuoshuozanbi",
		type : "get",
		async : true,
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				var num1 = result.data;
				var num2 = 100 - num1;
				myChart2.setOption({
					series : [ {
						type : 'pie',

						radius : radius,
						x : '0%', // for funnel
						itemStyle : labelFromatter,
						data : [ {
							name : 'other',
							value : num2,
							itemStyle : labelBottom
						}, {
							name : '',
							value : num1,
							itemStyle : labelTop
						} ]
					} ]
				});
			}
		},
		error : function() {
			alert("请求失败");
		}
	});

})();

//浏览器访问统计
(function() {
	var myChart = echarts.init(document.getElementById("index-pie-1"));
	$.ajax({
		url : "/browser",
		type : "get",
		async : true,
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				var num = result.data;
				myChart.setOption({
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b}: {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						x : 'left',
						data : [ 'Chrome', 'Firefox', 'Safari', 'Opera',
								'Other' ]
					},
					series : [ {
						name : '访问来源',
						type : 'pie',
						radius : [ '50%', '70%' ],
						avoidLabelOverlap : false,
						label : {
							normal : {
								show : false,
								position : 'center'
							},
							emphasis : {
								show : true,
								textStyle : {
									fontSize : '30',
									fontWeight : 'bold'
								}
							}
						},
						labelLine : {
							normal : {
								show : false
							}
						},
						data : [ {
							value : num[0],
							name : 'Chrome'
						}, {
							value : num[1],
							name : 'Firefox'
						}, {
							value : num[2],
							name : 'Safari'
						}, {
							value : num[3],
							name : 'Opera'
						}, {
							value : num[4],
							name : 'Other'
						} ]
					} ]
				});
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
})();

//最近发布说说统计
(function() {
	var myChart = echarts.init(document.getElementById("index-bar-1"));
	$.ajax({
		url : "/weekpublish",
		type : "get",
		async : true,
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				var num = result.data.post;
				myChart.setOption({
					color : [ '#3398DB' ],
					tooltip : {
						trigger : 'axis',
						axisPointer : { // 坐标轴指示器，坐标轴触发有效
							type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					toolbox : {
						show : true,
						feature : {
							//		            dataZoom: {
							//		                yAxisIndex: 'none'
							//		            },
							//		            dataView: {readOnly: false},
							magicType : {
								type : [ 'line', 'bar' ]
							},
						//		            restore: {},
						//		            saveAsImage: {}
						}
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						containLabel : true
					},
					xAxis : [ {
						type : 'category',
						data : [ getNowFormatDate(6), getNowFormatDate(5),
								getNowFormatDate(4), getNowFormatDate(3),
								getNowFormatDate(2), getNowFormatDate(1),
								getNowFormatDate(0) ],
						axisTick : {
							alignWithLabel : true
						}
					} ],
					yAxis : [ {
						type : 'value'
					} ],
					series : [ {
						name : '最新说说',
						type : 'bar',
						barWidth : '60%',
						data : [ num[0], num[1], num[2], num[3], num[4], num[5], num[6] ]
					} ]
				});
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
})();

//最近发布评论统计
(function() {
	var myChart = echarts.init(document.getElementById("index-line-1"));
	$.ajax({
		url : "/weekpublish",
		type : "get",
		async : true,
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				var num1 = result.data.comment;
				myChart.setOption({
					tooltip : {
						trigger : 'axis'
					},
					toolbox : {
						show : true,
						feature : {
							magicType : {
								type : [ 'line', 'bar' ]
							},
						}
					},
					xAxis : {
						type : 'category',
						boundaryGap : false,
						data : [ getNowFormatDate(6), getNowFormatDate(5),
								getNowFormatDate(4), getNowFormatDate(3),
								getNowFormatDate(2), getNowFormatDate(1),
								getNowFormatDate(0) ],
					},
					yAxis : {
						type : 'value',
						axisLabel : {
							formatter : '{value}'
						}
					},
					series : [
					{
						name : '最新评论',
						type : 'line',
						data : [ num1[0], num1[1], num1[2], num1[3], num1[4], num1[5],num1[6] ],
						markPoint : {
							data : [ {
								type : 'max',
								name : '最大值'
							}, {
								type : 'min',
								name : '最小值'
							} ]
						},
						markLine : {
							data : [ {
								type : 'average',
								name : '平均值'
							} ]
						}
					} ]
				});
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
})();
