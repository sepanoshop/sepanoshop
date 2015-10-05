/**
 * Plugin: jquery.zWeatherFeed
 * 
 * Version: 1.0.2
 * (c) Copyright 2010, Zazar Ltd
 * 
 * Description: jQuery plugin for display of Yahoo! Weather feeds
 * 
 * History:
 * 1.0.2 - Correction to options / link
 * 1.0.1 - Added hourly caching to YQL to avoid rate limits
 *         Uses Weather Channel location ID and not Yahoo WOEID
 *         Displays day or night background images
 *
 **/

(function($){

	var row = 'odd';

	$.fn.weatherfeed = function(locations, options) {	
	
		// Set pluign defaults
		var defaults = {
			unit: 'c',
			image: true,
			highlow: true,
			wind: true,
			link: false,
			showerror: true
		};  
		var options = $.extend(defaults, options); 
		
		// Functions
		return this.each(function(i, e) {
			var $e = $(e);
			
			// Add feed class to user div
			if (!$e.hasClass('weatherFeed')) $e.addClass('weatherFeed');

			// Check and append locations
			if (!$.isArray(locations)) return false;
			var count = locations.length;
			if (count > 10) count = 10;
			var locationid = '';
			for (var i=0; i<count; i++) {
				if (locationid != '') locationid += ',';
				locationid += "'"+ locations[i] + "'";
			}

			// Cache results for an hour to prevent overuse
			now = new Date()
					
			// Create Yahoo Weather feed API address
			var query = "select * from weather.forecast where location in ("+ locationid +") and u='"+ options.unit +"'";
			var api = 'http://query.yahooapis.com/v1/public/yql?q='+ encodeURIComponent(query) +'&rnd='+ now.getFullYear() + now.getMonth() + now.getDay() + now.getHours() +'&format=json&callback=?';

			// Send request
			//$.getJSON(api, function(data) {
			$.ajax({
				type: 'GET',
				url: api,
				dataType: 'json',
				success: function(data) {

					if (data.query) {
			
						if (data.query.results.channel.length > 0 ) {
							
							// Multiple locations
							var result = data.query.results.channel.length;
							for (var i=0; i<result; i++) {
							
								// Create weather feed item
								_callback(e, data.query.results.channel[i], options);
							}
						} else {

							// Single location only
							_callback(e, data.query.results.channel, options);
						}
					} else {
						if (options.showerror) $e.html('<p>Weather information unavailable</p>');
					}
				},
				error: function(data) {
					if (options.showerror)  $e.html('<p>Weather request failed</p>');
				}
			});

		});
	};

	// Function to each feed item
	var _callback = function(e, feed, options) {
		var $e = $(e);

		// Format feed items
		var wd = feed.wind.direction;
		if (wd>=348.75&&wd<=360){wd="N"};if(wd>=0&&wd<11.25){wd="N"};if(wd>=11.25&&wd<33.75){wd="NNE"};if(wd>=33.75&&wd<56.25){wd="NE"};if(wd>=56.25&&wd<78.75){wd="ENE"};if(wd>=78.75&&wd<101.25){wd="E"};if(wd>=101.25&&wd<123.75){wd="ESE"};if(wd>=123.75&&wd<146.25){wd="SE"};if(wd>=146.25&&wd<168.75){wd="SSE"};if(wd>=168.75&&wd<191.25){wd="S"};if(wd>=191.25 && wd<213.75){wd="SSW"};if(wd>=213.75&&wd<236.25){wd="SW"};if(wd>=236.25&&wd<258.75){wd="WSW"};if(wd>=258.75 && wd<281.25){wd="W"};if(wd>=281.25&&wd<303.75){wd="WNW"};if(wd>=303.75&&wd<326.25){wd="NW"};if(wd>=326.25&&wd<348.75){wd="NNW"};
		var wf = feed.item.forecast[0];
		
		// Determine day or night image
		wpd = feed.item.pubDate;
		n = wpd.indexOf(":");
		tpb = _getTimeAsDate(wpd.substr(n-2,8));
		tsr = _getTimeAsDate(feed.astronomy.sunrise);
		tss = _getTimeAsDate(feed.astronomy.sunset);

		if (tpb>tsr && tpb<tss) {daynight = 'd';} else {daynight = 'n';}

		// Add item container
		var html = '<div class="weatherItem '+ row +'"';
		if (options.image) {html += ' style="text-align: center;"'};
		html += '>';
		
		// Add item data
		html += '<div class="weatherCity" style="text-align: center;padding: 0px;height:50px;"><img style="position: relative;width:100px;left:20px" src='+'http://l.yimg.com/a/i/us/nws/weather/gr/'+ feed.item.condition.code + daynight +'.png'+'  /></div>';
		html += '<div class="weatherCity"  style="text-align: center;padding: 0px">'+ feed.location.city.toString().replace("Esfahan", "اصفهان") +'</div>';
		html += '<div class="weatherTemp" style="text-align: center;">'+ feed.item.condition.temp +'&deg;</div>';
		html += '<div class="weatherDesc" style="text-align: center;">'+ convertWhetherStatement(feed.item.condition.text) +'</div>';
		if (options.highlow) html += '<div class="weatherRange"  style="text-align: center;"> درجه حرارت از  &deg;'+ wf.low +' تا &deg;'+ wf.high +'</div>';
		if (options.wind) html += '<div class="weatherWind"  style="text-align: center;">وزش باد: '+ wd +' '+ feed.wind.speed + feed.units.speed +'</div>';
		if (options.link) html += '<div class="weatherLink"  style="text-align: center;"><a href="'+ feed.item.link +'">Read full forecast</a></div>';
		html += '</div>';

		// Alternate row classes
		if (row == 'odd') {row = 'even';} else {row = 'odd';}
		
		$e.append(html);
	};

	// Get time string as date
	var _getTimeAsDate = function(t) {
		
		d = new Date();
		r = new Date(d.toDateString() +' '+ t);

		return r;
	};
        var convertWhetherStatement = function(Statement) {
        var s = "";
        
        if (Statement.toLowerCase()==("Haze".toLowerCase())) {
            s = "مه خفیف";
        } else if (Statement.toLowerCase()==("Cloudy".toLowerCase())) {
            s = "ابری";
        } else if (Statement.toLowerCase()==("tornado".toLowerCase())) {
            s = "طوفان";
        } else if (Statement.toLowerCase()==("tropical storm".toLowerCase())) {
            s = "طوفان منطقه حاره";
        } else if (Statement.toLowerCase()==("Thunder/Windy".toLowerCase())) {
            s = "باد و رعدوبرق";
        } else if (Statement.toLowerCase()==("hurricane".toLowerCase())) {
            s = "تند باد";
        } else if (Statement.toLowerCase()==("severe thunderstorms".toLowerCase())) {
            s = "طوفان تندری شدید";
        } else if (Statement.toLowerCase()==("thunderstorms".toLowerCase())) {
            s = "طوفان تندری";
        } else if (Statement.toLowerCase()==("mixed rain and snow".toLowerCase())) {
            s = "باران و برف";
        } else if (Statement.toLowerCase()==("drizzle".toLowerCase())) {
            s = "باران ریز";
        } else if (Statement.toLowerCase()==("freezing rain".toLowerCase())) {
            s = "تگرگ";
        } else if (Statement.toLowerCase()==("showers".toLowerCase())) {
            s = "درشت باران";
        } else if (Statement.toLowerCase()==("blowing snow".toLowerCase())) {
            s = "بوران";
        } else if (Statement.toLowerCase()==("snow".toLowerCase())) {
            s = "برف";
        } else if (Statement.toLowerCase()==("hail".toLowerCase())) {
            s = "تگرگ";
        } else if (Statement.toLowerCase()==("sleet".toLowerCase())) {
            s = "برف و باران";
        } else if (Statement.toLowerCase()==("dust".toLowerCase())) {
            s = "گردوغبار";
        } else if (Statement.toLowerCase()==("foggy".toLowerCase())) {
            s = "مه";
        } else if (Statement.toLowerCase()==("smoky".toLowerCase())) {
            s = "دود گرفته";
        } else if (Statement.toLowerCase()==("blustery".toLowerCase())) {
            s = "پر باد";
        } else if (Statement.toLowerCase()==("windy".toLowerCase())) {
            s = "پرباد";// بدخیز
        } else if (Statement.toLowerCase()==("cold".toLowerCase())) {
            s = "سرد";
        } else if (Statement.toLowerCase()==("mostly cloudy".toLowerCase())) {
            s = "تغریبا تمام ابری";
        } else if (Statement.toLowerCase()==("partly cloudy".toLowerCase())) {
            s = "تا قسمتی ابری";
        } else if (Statement.toLowerCase()==("sunny".toLowerCase())) {
            s = "آفتابی";
        } else if (Statement.toLowerCase()==("fair".toLowerCase())) {
            s = "نسبتا بدون ابر";
        } else if (Statement.toLowerCase()==("mixed rain and hail".toLowerCase())) {
            s = "باران و تگرگ";
        } else if (Statement.toLowerCase()==("hot".toLowerCase())) {
            s = "گرم و سوزان";
        } else if (Statement.toLowerCase()==("scattered thunderstorms".toLowerCase())) {
            s = "رعد و برق پراکنده";
        } else if (Statement.toLowerCase()==("scattered showers".toLowerCase())) {
            s = "برف پراکنده";
        } else if (Statement.toLowerCase()==("heavy snow".toLowerCase())) {
            s = "برف شدید";
        } else if (Statement.toLowerCase()==("scattered snow showers".toLowerCase())) {
            s = "ریز برف پراکنده";
        } else if (Statement.toLowerCase()==("heavy snow".toLowerCase())) {
            s = "برف سنگین";
        } else if (Statement.toLowerCase()==("partly cloudy".toLowerCase())) {
            s = "تا قسمتی ابری";
        } else if (Statement.toLowerCase()==("Light Rain Shower".toLowerCase())) {
            s = "رگبار و باران نسبتا سبک";
        } else if (Statement.toLowerCase()==("Thunder in the Vicinity".toLowerCase())) {
            s = "همراه با باد محلی";
        } else if (Statement.toLowerCase()==("Rain Shower".toLowerCase())) {
            s = "باران پراکنده";
        } else {
            s = "";
        }
        return s;
    };
})(jQuery);