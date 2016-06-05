angular.module('HttpInterceptor', [
        'AppNotificationsService',
        'CookieStorageService',
        'ui.router'
    ])

    .factory('HttpInterceptor', ['$q', '$injector', 'CookieStorageService', 'AppNotificationsService', 'toastr', function ($q, $injector, CookieStorageService, AppNotificationsService, toastr) {

        var LOGIN_STATE = 'signIn';

        function handleUnauthorized() {
            CookieStorageService.removeXAuthToken();
            AppNotificationsService.loginRequired();
            return $q.defer().promise;
        }

        function handleInternalServerError() {
            toastr.error("Internal Error Please contact system administrator");
        }

        function handleFrobidden() {
            toastr.error("Access Denided You have insufficient privileges");
        }

        function handleError(rejection) {
            var currentState = $injector.get('$state').current.name;
            if (rejection.status === 401 && currentState !== LOGIN_STATE) {
                handleUnauthorized();
            } else if (rejection.status === 403) {
                handleFrobidden();
            } else if (rejection.status === 500) {
                handleInternalServerError();
            }
            return $q.reject(rejection);
        }

        return {
            responseError: handleError
        };
    }]);


