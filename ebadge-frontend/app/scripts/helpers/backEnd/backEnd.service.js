'use strict';

angular.module('ebadgeFrontendApp')
.factory('backEnd', function ($rootScope, $http, $q, backEndUrl) {
  $http.defaults.headers.common['Content-Type'] = 'application/json';

  function _call (method, url, data) {
    var deferred = $q.defer();

    $http({ method: method, url: backEndUrl+url, data: data })
      .success(function (result) {
        console.log(result);
        deferred.resolve(result);
      })
      .error(function (error) {
        deferred.reject(error);
      });

    return deferred.promise;
  }

  return {
    call: _call
  };
});