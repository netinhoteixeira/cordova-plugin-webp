
var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var WebPEncoderDecoder = function() {
};

WebPEncoderDecoder.prototype.encode = function(data, successCallback, errorCallback) {
    return exec(successCallback, errorCallback, "Camera", "encode", [{
    	"data": data
    }]);
};

WebPEncoderDecoder.prototype.decode = function(data, successCallback, errorCallback) {
    return exec(successCallback, errorCallback, "Camera", "decode", [{
    	"data": data
    }]);
};

module.exports = WebPEncoderDecoder;
