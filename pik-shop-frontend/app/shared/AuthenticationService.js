angular
    .module('AuthenticationService', [
        'restangular',
        'CookieStorageService'
    ])
    .factory('AuthenticationService', ['Restangular', 'CookieStorageService', 'UserStorageService', 'AppNotificationsService', '$http', '$q', AuthenticationService]);

function AuthenticationService(Restangular, CookieStorageService, UserStorageService, AppNotificationsService, $http, $q) {

    return {
        signIn: signIn,
        logout: logout

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

    function logout() {
        return Restangular.one('logout').get()
            .then(function (response) {
                UserStorageService.clearLoggedUser();
                CookieStorageService.removeXAuthToken();
                AppNotificationsService.logoutConfirmed();
            }, function (response) {
                UserStorageService.clearLoggedUser();
                CookieStorageService.removeXAuthToken();
                AppNotificationsService.logoutConfirmed();
            });
    }
}