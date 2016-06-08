angular
    .module('SignUpService', [
        'restangular'
    ])
    .factory('SignUpService', ['Restangular', '$http', '$q', AuthenticationService]);

function AuthenticationService(Restangular, $http, $q) {

    return {
        signUpNewUser: signUpNewUser

    };
    function signUpNewUser(newUser) {
        return Restangular.all('public/signup').post(newUser);
    }

}