angular
    .module('UsersService', ['restangular'])
    .factory('UsersService', ['Restangular', UsersService]);

function UsersService(Restangular){

    return {
        getUsersList: getUsersList
    };

    // function getUsersList() {
    //     return Restangular.one('users').get();
    // }

    function getUsersList(params) {
        var sortingCol = Object.keys(params.sorting())[0];
        var request = {
            page: params.page() - 1,
            size: params.count(),
            id: params.filter().id,
            name: params.filter().name,
            email: params.filter().email,
            sortCol: sortingCol,
            direction: params.sorting()[sortingCol]
        };

        return Restangular.one('users').get(request);
    }
}