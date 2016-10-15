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
    backEnd.call('GET', 'visitors').then((response) => {
      this.requests = response._embedded.visitors.filter((visitor) => {
        return visitor.status === 'PENDING';
      });
    });

    this.accept = function (request) {
      console.log('accept:', request);

      backEnd.call('POST', 'visitor/accept', { visitorId: request.visitorId }).then((response) => {
        console.log(response)
        backEnd.call('GET', 'visitors').then((response) => {
          this.requests = response._embedded.visitors.filter((visitor) => {
            return visitor.status === 'PENDING';
          });
        });
      });
    };

    this.reject = function (request) {
      console.log('reject:', request);

      backEnd.call('POST', 'visitor/reject', { visitorId: request.visitorId }).then((response) => {
        backEnd.call('GET', 'visitors').then((response) => {
          this.requests = response._embedded.visitors.filter((visitor) => {
            return visitor.status === 'PENDING';
          });
        });
      });
    };
  }]);
