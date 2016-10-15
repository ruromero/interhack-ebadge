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
        return visitor.status !== 'pending';
      });

      console.log(this.requests);
    });

    this.accept = function (request) {
      console.log('accept:', request);

      //backEnd.call('POST', 'accept').then((response) => {
      //  console.log(response);
      //});
    };

    this.reject = function (request) {
      console.log('reject:', request);

      //backEnd.call('POST', 'reject').then((response) => {
      //  console.log(response);
      //});
    };
  }]);
