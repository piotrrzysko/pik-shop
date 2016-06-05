angular
    .module('app.home', [
        'app.signUp'
    ])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: '/app/components/home/home.html',
                controller: 'HomeController',
                resolve: {
                    currentUser: ['UserStorageService', function (UserStorageService) {
                        if (UserStorageService.hasLoginCookie()) {
                            return UserStorageService.fetchLoggedUser();
                        }
                        return UserStorageService.getCurrentUser();
                    }]
                }
            });
    }])
    .controller('HomeController', ['$rootScope', '$state', '$scope', 'Restangular', 'toastr', 'UserStorageService', 'AuthenticationService', HomeController]);

function HomeController($rootScope, $state, $scope, Restangular, toastr, UserStorageService, AuthenticationService) {
    var home = this;

    home.testUnAuthorized = function () {
        Restangular.one('hello').get();
    };

    home.isSignedIn = function () {
        return UserStorageService.isSignedIn();
    };

    home.logout = function () {
        return AuthenticationService.logout();
    };

    $scope.sportImages = [{
        id: 1,
        text: "Hello",
        image: '/assets/img/img1.png'
    }];

    $scope.myInterval = 3000;
    $scope.noWrapSlides = false;
    $scope.active = 0;
    var slides = $scope.slides = [];
    var currIndex = 0;

    $scope.addSlide = function (i) {
        var newWidth = 600 + slides.length + 1;
        slides.push({
            image: 'assets/img/img' + i + '.png',
            text: ['opis produktu 1', 'Jeszcze ciekawszy opis produktu 2', 'No tego opisu to juz nic nie przebije! Opis produktu 3', 'I love that'][slides.length % 4],
            id: currIndex++
        });
    };


    for (var i = 0; i < 3; i++) {
        $scope.addSlide(i + 1);
    }


    $scope.availableSearchParams = [
        {key: "name", name: "nazwa", placeholder: "Nazwa..."},
        {key: "price", name: "cena", placeholder: "Cena..."},
        {key: "quantity", name: "ilość", placeholder: "Ilość..."},
    ];

    $scope.searchParams = {};

    $rootScope.$on('event:auth-loginRequired', function () {
        toastr.error("Wymagane zalogowanie");
    });

    $rootScope.$on('event:auth-loginConfirmed', function () {
        $state.go('profile');
    });

    $rootScope.$on('event:auth-logoutConfirmed', function () {
        $state.go('home');
    });

}