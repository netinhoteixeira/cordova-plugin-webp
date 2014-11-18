
var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var WebPEncoderDecoder = function() {
};

WebPEncoderDecoder.encode = function(data, successCallback, errorCallback) {
    return exec(successCallback, errorCallback, "WebPEncoderDecoder", "encode", [{
    	"data": data
    }]);
};

WebPEncoderDecoder.decode = function(data, successCallback, errorCallback) {
    return exec(successCallback, errorCallback, "WebPEncoderDecoder", "decode", [{
    	"data": data
    }]);
};

module.exports = WebPEncoderDecoder;
