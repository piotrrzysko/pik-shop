module.exports = function(config) {
  config.set({

    basePath: './',

    files: [
      'bower_components/angular/angular.js',
      'bower_components/angular-route/angular-route.js',
      'bower_components/angular-ui-router/release/angular-ui-router.js',
      'bower_components/ng-table/dist/ng-table.js',
      'bower_components/lodash/dist/lodash.js',
      'bower_components/restangular/dist/restangular.js',
      'bower_components/angular-mocks/angular-mocks.js',
      'app/components/**/*.js',
      'app/shared/*.js',
      'app/*.js',
      'e2e-tests/scenarios.js'
    ],

    autoWatch: true,

    frameworks: ['jasmine'],

    browsers: ['Chrome'],

    plugins: [
      'karma-chrome-launcher',
      'karma-firefox-launcher',
      'karma-jasmine',
      'karma-junit-reporter'
    ],

    junitReporter: {
      outputFile: 'test_out/unit.xml',
      suite: 'unit'
    }

  });
};
