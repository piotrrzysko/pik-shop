angular
    .module('filters', [])
    .filter('filePath', function () {
        return function (input) {
            return ENV.filesRepoAlias + input;
        }
    });
