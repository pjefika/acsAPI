/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Vue.component("alertpanel", {
    props: {
        mensagem: {
            type: String,
            default: ''
        },
        erro: {
            type: String,
            default: ''
        }
    },
    template: "\
            <div>\n\
                <div v-show='mensagem' :class='leClass' style='font-size:15px;margin-top:10px'>\n\
                    {{mensagem}}\n\
                </div>\n\
            </div>",
    computed: {
        leClass: function() {
            if (!this.erro) {
                return 'alert alert-success';
            }
            return 'alert alert-danger';
        }

    }
});
