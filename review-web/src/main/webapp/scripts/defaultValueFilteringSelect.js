/*
 * jQuery.splitter.js - two-pane splitter window plugin
 *
 * version 1.51 (2009/01/09) 
 * 
 * Dual licensed under the MIT and GPL licenses: 
 *   http://www.opensource.org/licenses/mit-license.php 
 *   http://www.gnu.org/licenses/gpl.html 
 */

/**
 * The splitter() plugin implements a two-pane resizable splitter window.
 * The selected elements in the jQuery object are converted to a splitter;
 * each selected element should have two child elements, used for the panes
 * of the splitter. The plugin adds a third child element for the splitbar.
 * 
 * For more details see: http://methvin.com/splitter/
 *
 *
 * @example $('#MySplitter').splitter();
 * @desc Create a vertical splitter with default settings 
 *
 * @example $('#MySplitter').splitter({type: 'h', accessKey: 'M'});
 * @desc Create a horizontal splitter resizable via Alt+Shift+M
 *
 * @name splitter
 * @type jQuery
 * @param Object options Options for the splitter (not required)
 * @cat Plugins/Splitter
 * @return jQuery
 * @author Dave Methvin (dave.methvin@gmail.com)
 */
 
dojo.provide("myDojo.DefaultValueFilteringSelect");
dojo.require("dijit.form.FilteringSelect");
dojo.declare("myDojo.DefaultValueFilteringSelect", /*cream un widget nou de tip select care ne satisface placerile*/
   	dijit.form.FilteringSelect, /*mostenim FilteringSelect, care la randul sau deriva din ComboBox (si nu numai)*/
    {
		startup: function(){ /*aici selectul se considera creat si complet, presupun ca dupa startup() nu se mai apeleaza alte functii*/
    		this.inherited(arguments);
		}, 
		defaultValue: '' /*atribut nou = valoarea implicita a selectului*/
						 /*defaultValue trebuia sa ia valoarea indicata in primul option al selectului altfel nu va merge required*/,
		isValid:function(){/*is valid se apeleaza pentru a vedea daca selectul este valid*/
			//console.log('IsValid: '+this.get("value")+" | "+this._onChangeActive);
			if(this.required)
    			if(this._onChangeActive && this.get("value")==this.defaultValue) return false;
			return this._isvalid;
		}, 
        getErrorMessage: function(isFocused){ /*getErrorMessage() este chemata daca si numai daca isValid() intoarce false*/
            /*verificam la required*/
			return (this.required && this.get('value') == this.defaultValue) ? this.missingMessage : this.invalidMessage;
		}, 
		callbackOnChange: function(evt){},
		onChange: function(evt){
			this.inherited(arguments);
			this.validate(true);
			this.callbackOnChange.call(this,evt); /*chemam functia de callback la onChange care este indicata in tagul selectbox la atributul callbackOnChange*/
		}
    }
);

/**
 * The ValidationTextarea type is extended from a Textarea field and has the required attribute validation.
 */
dojo.provide("ValidationTextarea");
dojo.require("dijit.form.Textarea");
dojo.require("dijit.form.ValidationTextBox");

dojo.declare("ValidationTextarea", [dijit.form.ValidationTextBox,dijit.form.SimpleTextarea],
{

  invalidMessage: "This field is required",

  regExp: '(.|\\s)*',

  postCreate: function() {
	this.inherited(arguments);
  },

   validate: function() {
    if (arguments.length==0) {
    return this.validate(false);
    }
    return this.inherited(arguments);
    },

  validate: function(isFocused) {
	if (arguments.length==0) {
	  return this.validate(false);
	}
	return this.inherited(arguments);
  },

  onFocus: function() {
	if (!this.isValid()) {
	  this.displayMessage(this.getErrorMessage());
	}
  },

  onBlur: function() {
	this.validate(false);
	if (!this.isValid()) {
	  this.displayMessage(this.getErrorMessage());
	}
  }
});
