angular.module('CookieStorageService', [
        'ipCookie'
    ])
    .factory('CookieStorageService', ['ipCookie', function (ipCookie) {
        var X_AUTH_TOKEN = 'X-Auth-Token';
        var headers = {'X-Auth-Token': getXAuthToken()};

        function saveXAuthToken(xAuthToken) {
            ipCookie(X_AUTH_TOKEN, xAuthToken);
        }

        function getXAuthToken() {
            return ipCookie(X_AUTH_TOKEN);
        }

        return {
            setXAuthToken: function (xAuthToken) {
                headers[X_AUTH_TOKEN] = xAuthToken;
                saveXAuthToken(xAuthToken);
            },
            removeXAuthToken: function () {
                headers[X_AUTH_TOKEN] = '';
                saveXAuthToken('');
            },
            getAuthHeaders: function () {
                return headers;
            }
        };
    }]);

