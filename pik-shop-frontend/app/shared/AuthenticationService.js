angular
    .module('AuthenticationService', [
        'restangular',
        'CookieStorageService'
    ])
    .factory('AuthenticationService', ['Restangular', 'CookieStorageService', '$http', '$q', AuthenticationService]);

function AuthenticationService(Restangular, CookieStorageService, $http, $q) {

    return {
        signIn: signIn

    };
    function getToken(username, password) {
        var httpConfig = {};
        httpConfig.method = 'POST';
        httpConfig.url = ENV.apiURL + '/login';
        httpConfig.headers = {'Content-Type': 'application/json'};
        httpConfig.data = {username: username, password: password};
        return $http(httpConfig);
    }

    function signIn(userName, password) {

        var defered = $q.defer();
        getToken(userName, password)
            .success(function (response) {
                CookieStorageService.setXAuthToken(response.token);
                Restangular.setDefaultHeaders(CookieStorageService.getAuthHeaders());
                defered.resolve();
            })
            .error(function (response) {
                defered.reject(response);
            });
        return defered.promise;
    }
}