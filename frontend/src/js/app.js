const app = {};
$( document ).ready(function() {

    //app global config variables..
    app.config = {
        productPriceUrl: 'http://localhost:8080/products/{PRODUCT}/prices',
        commonCoinsURL: 'http://localhost:8080/products',
    };

    //set case of coin input box...
    app.setUpperCase = function(){
        $(".typeahead").keyup(function( event ) {
            event.target.value = event.target.value.toString().toUpperCase();
        });
    }; 
    
    //Reset cards to default state...
    app.resetCards = function(){
        $("input.form-control-plaintext:text").removeClass('lower higher');
        $("input.form-control-plaintext:text").val('');
    };

    app.setFindBtnOnClick = function(){
        $( "#find-btn" ).click(function( event ) {
            const value = $('#coin-input').val()
            
            app.resetCards();

            if (value.length > 0) {
                $.ajax({
                    url: app.config.productPriceUrl.replace('{PRODUCT}', value),
                    crossDomain: true,
                    beforeSend: function( xhr ) {
                      xhr.overrideMimeType( "application/json; charset=UTF8" );
                    }
                  }).done(function( data ) {        
                    console.log( "Got data...", data);

                    let minPrice = data.reduce((min, p) => p.price < min ? p.price : min, data[0].price);
                    let maxPrice = data.reduce((max, p) => p.price > max ? p.price : max, data[0].price);
                    ['btx','bfx','bnb'].map(_exchange => {
                        let exchangeData = data.find(item => {return item.exchange == _exchange.toUpperCase()});   

                        let input = $('#' + _exchange + '-input');    
                        if (typeof exchangeData == 'undefined') {
                            input.val('Not available. Sorry!');                            
                        }else{
                            input.val(exchangeData.price);
                        
                            if (exchangeData.price == minPrice) {
                                input.addClass('lower');
                            }else if(exchangeData.price == maxPrice){
                                input.addClass('higher');
                            }    
                        }                            
                    })
                });
            };

        });
    };

    //load coins as list to dom...
    app.loadCoins = function () {
        $.ajax({
            url: this.config.commonCoinsURL,
            crossDomain: true,
            beforeSend: function( xhr ) {
              xhr.overrideMimeType( "application/json; charset=UTF8" );
            }
          }).done(function( data ) {        
            console.log( "Got data...", data.length);
            $(".typeahead").typeahead({
                source: data
            });
        });
    };

    //Contain all app setup functions...
    app.startup = function(){
        this.loadCoins();
        this.setUpperCase();
        this.setFindBtnOnClick();
        app.resetCards();
    };

    app.startup();

});

