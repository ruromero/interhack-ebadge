'use strict';

angular.module('ebadgeFrontendApp')
.factory('backEnd', function ($rootScope, $http, $q, backEndUrl) {
  $http.defaults.headers.common['Content-Type'] = 'application/json';

  function _login (email, password) {
    var deferred = $q.defer();

    // Authenticate
    _call('POST', 'authenticate', { email: email, password: password }).then(
      function (result) {
        if (result.hasOwnProperty('authToken') && result.authToken.hasOwnProperty('token') && result.hasOwnProperty('authUser')) {
          $http.defaults.headers.common['X-Auth-Token'] = result.authToken.token;

          deferred.resolve(result.authUser);
        }
        else {
          deferred.reject('User not found');
        }
      },
      function (error) {
        deferred.reject(error);
      }
    );

    return deferred.promise;
  }

  function _logout () {
    var deferred = $q.defer();

    _call('POST', 'signout').then(
      function () {
        $http.defaults.headers.common['X-Auth-Token'] = undefined;
        deferred.resolve();
      },
      function (error) {
        deferred.reject(error);
      }
    );

    return deferred.promise;
  }

  function _call (method, url, data) {
    var deferred = $q.defer();

    $http({ method: method, url: backEndUrl+url, data: data })
      .success(function (result) {
        deferred.resolve(result);
      })
      .error(function (error) {
        deferred.reject(error);
      });

    return deferred.promise;
  }

  return {
    login: _login,
    logout: _logout,
    call: _call
  };
});