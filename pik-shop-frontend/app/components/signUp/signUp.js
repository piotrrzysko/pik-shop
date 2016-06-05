angular.module('app.signUp', [
    'ui.router'
]).controller('SignUpController', ['$scope', 'SignUpService', SignUpController]);

function SignUpController($scope, SignUpService) {
    $scope.signUpRequest = {};

    $scope.sendSignUpRequest = function () {
        SignUpService.signUpNewUser($scope.signUpRequest);
    }
}