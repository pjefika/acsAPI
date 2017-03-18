/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Vue */

Vue.config.devtools = true;
Vue.config.silent = true;

var vm = new Vue({
    el: "#instancia",
    data: {
        modal: {
            comp: '',
            titulo: 'Carregando...'
        },
        currentView: 'searchAction',
        notif: {
            mensagem: 'Default',
            tipo: 'danger'
        }
    },
    created: function() {
    },
    watch: {
        notif: function(val) {
            $.notify({
                // options
                message: val.mensagem
            }, {
                // settings
                type: val.tipo
            });
        }
    }
});



vm.$on('notif', function(msg) {
    this.notif = {
        mensagem: msg,
        tipo: 'danger'
    };
})
