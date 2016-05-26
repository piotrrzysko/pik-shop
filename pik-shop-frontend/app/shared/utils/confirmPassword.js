angular.module('app.utils.confirmPassword', [ ])

    .directive('confirmPassword', ['defaultErrorMessageResolver', function(defaultErrorMessageResolver) {
        defaultErrorMessageResolver.getErrorMessages().then(function (errorMessages) {
            errorMessages['confirmPassword'] = 'Please ensure the passwords match.';
        });

        return {
            restrict : 'A',
            require : 'ngModel',
            scope : {
                confirmPassword : '='
            },
            link : function(scope, element, attributes, ngModel) {
                ngModel.$validators.confirmPassword = function(modelValue) {
                    return modelValue === scope.confirmPassword;
                };

                scope.$watch('confirmPassword', function() {
                    ngModel.$validate();
                });
            }
        };
    }
    ]);