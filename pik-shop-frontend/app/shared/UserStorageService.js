angular.module('UserStorageService', [])

    .factory('UserStorageService', ['$q', 'Restangular', 'CookieStorageService', function ($q, Restangular, CookieStorageService) {

        var currentUser = {};

        return {
            hasLoginCookie: function () {
                return CookieStorageService.getAuthHeaders()['X-Auth-Token'];
            },
            setLoggedUser: function (user) {
                currentUser = user;
            },
            clearLoggedUser: function () {
                currentUser = {};
            },
            getCurrentUser: function () {
                return currentUser;
            },
            fetchLoggedUser: function () {
                var deferred = $q.defer();
                var that = this;
                Restangular.one('/users/logged').get()
                    .then(function (user) {
                        that.setLoggedUser(user);
                        deferred.resolve(user);
                    }, function () {
                        deferred.reject();
                    });
                return deferred.promise;
            },
            isSignedIn: function () {
                return currentUser.id;
            },
            isAdmin: function () {
                return currentUser.userType === "ADMIN";
            },
            isUser: function () {
                return currentUser.userType === "USER";
            }
        };
    }]);
