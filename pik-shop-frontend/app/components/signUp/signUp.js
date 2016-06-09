angular.module('app.signUp', [
    'ui.router'
]).controller('SignUpController', ['$scope', 'SignUpService', 'AppNotificationsService', SignUpController]);

function SignUpController($scope, SignUpService, AppNotificationsService) {
    $scope.signUpRequest = {};

    $scope.sendSignUpRequest = function () {
        SignUpService.signUpNewUser($scope.signUpRequest).then(function () {
            AppNotificationsService.signUpConfirmed();
        }, function () {
            toastr.error("Błąd rejestracji");
        });
;
    }
}
