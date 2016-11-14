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
    backEnd.call('GET', 'visitors').then(function(response) {
      this.requests = response._embedded.visitors.filter(function(visitor) {
        return visitor.status === 'PENDING';
      });
    });

    this.accept = function (request) {
      backEnd.call('POST', 'visitor/accept', { visitorId: request.visitorId }).then(function(response) {
        console.log(response)
        backEnd.call('GET', 'visitors').then(function(response) {
          this.requests = response._embedded.visitors.filter(function(visitor) {
            return visitor.status === 'PENDING';
          });
        });
      });
    };

    this.reject = function (request) {
      backEnd.call('POST', 'visitor/reject', { visitorId: request.visitorId }).then(function(response) {
        backEnd.call('GET', 'visitors').then(function(response) {
          this.requests = response._embedded.visitors.filter(function(visitor) {
            return visitor.status === 'PENDING';
          });
        });
      });
    };
  }]);
