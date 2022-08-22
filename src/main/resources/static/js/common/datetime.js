/*更新日期时间*/
function updateDateTime() {
    let date = new Date();

    //获取时分秒
    var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours()
    var min = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()
    var sec = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds()
    var time = hour + ":" + min + ":" + sec;

    //获取年月日
    var year = date.getFullYear()
    var month = date.getMonth() + 1  /*显示当前月份-1，所以+1才能显示当前月份*/
    var day = date.getDate()

    //获取星期几
    var weeks = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"]
    var nowWeek = weeks[date.getDay()];

    /*
    if (today.getHours() <= 24) time = "晚上好";
	if (today.getHours() <= 19) time = "傍晚好";
	if (today.getHours() <= 17) time = "下午好";
	if (today.getHours() <= 14) time = "中午好";
	if (today.getHours() <= 12) time = "上午好";
	if (today.getHours() <= 8) time = "早上好";
	if (today.getHours() <= 5) time = "凌晨好";
	*/
    var greet=null;
    //根据不同的时间显示不同的问候
    // 下面采用测试纠正法对greet进行赋值
    if (date.getHours() <= 24) greet = "晚上好";
    if (date.getHours() <= 19) greet = "傍晚好";
    if (date.getHours() <= 17) greet = "下午好";
    if (date.getHours() <= 14) greet = "中午好";
    if (date.getHours() <= 12) greet = "上午好";
    if (date.getHours() <= 8) greet = "早上好";
    if (date.getHours() <= 5) greet = "凌晨好";

    var result = year + "年  " + month + "月" + day + "日  " + nowWeek + "  " + time+" "+greet;

    /*这里的dateTime对应显示位置的id值*/
    $("#dateTime").text(result)/*底部栏显示*/
    $("#dateTime1").text(greet)/*导航栏显示*/
}