function AjaxHelper() {
    this.ajax = function(url, type, dataType, data, callback) {
        $.ajax({
            url: url,
            type: type,
            dataType: dataType,
            data: data,
            success: callback,
            error: function(xhr, errorType, error) {
                vm.$message.error('服务器开小差了');
            }
        })
    }
}
AjaxHelper.prototype.get = function(url, data, callback) {
    this.ajax(url, 'GET', 'JSONP', data, callback)
}
AjaxHelper.prototype.post = function(url, data, callback) {
    this.ajax(url, 'POST', 'JSONP', data, callback)
}

AjaxHelper.prototype.put = function(url, data, callback) {
    this.ajax(url, 'PUT', 'JSONP', data, callback)
}

AjaxHelper.prototype.delete = function(url, data, callback) {
    this.ajax(url, 'DELETE', 'JSONP', data, callback)
}

AjaxHelper.prototype.jsonp = function(url, data, callback) {
    this.ajax(url, 'GET', 'JSONP', data, callback)
}

AjaxHelper.prototype.constructor = AjaxHelper