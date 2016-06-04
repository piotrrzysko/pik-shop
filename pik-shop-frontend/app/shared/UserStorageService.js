angular.module('UserStorageService', [])

    .factory('UserStorageService', [function () {

        var currentUser;

        return {
            setLoggedUser: function (user) {
                currentUser = user;
            },
            clearLoggedUser: function () {
                currentUser = undefined;
            },
            getCurrentUser: function () {
                return currentUser;
            }
        };
    }]);
