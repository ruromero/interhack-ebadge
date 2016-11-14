'use strict';

/**
 * @ngdoc function
 * @name ebadgeFrontendApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the ebadgeFrontendApp
 */
angular.module('ebadgeFrontendApp')
  .controller('GrantCtrl', [ 'backEnd', function (backEnd) {
    
    var ctrl = this;

    backEnd.call('GET', 'visitors').then(function(response) {
      ctrl.requests = response._embedded.visitors.filter(function(visitor) {
        return visitor.status === 'PENDING';
      });
    });

    ctrl.accept = function (request) {
      backEnd.call('POST', 'visitor/accept', { visitorId: request.visitorId }).then(function(response) {
        console.log(response)
        backEnd.call('GET', 'visitors').then(function(response) {
          ctrl.requests = response._embedded.visitors.filter(function(visitor) {
            return visitor.status === 'PENDING';
          });
        });
      });
    };

    ctrl.reject = function (request) {
      backEnd.call('POST', 'visitor/reject', { visitorId: request.visitorId }).then(function(response) {
        backEnd.call('GET', 'visitors').then(function(response) {
          ctrl.requests = response._embedded.visitors.filter(function(visitor) {
            return visitor.status === 'PENDING';
          });
        });
      });
    };
  }]);
