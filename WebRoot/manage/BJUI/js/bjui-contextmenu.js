/*!
 * B-JUI v1.0 (http://b-jui.com)
 * Git@OSC (http://git.oschina.net/xknaan/B-JUI)
 * Copyright 2014 K'naan (xknaan@163.com).
 * Licensed under Apache (http://www.apache.org/licenses/LICENSE-2.0)
 */

/* ========================================================================
 * B-JUI: bjui-contextmenu.js v1.0
 * @author K'naan (xknaan@163.com)
 * -- Modified from dwz.contextmenu.js (author:ZhangHuihua@msn.com)
 * http://git.oschina.net/xknaan/B-JUI/blob/master/BJUI/js/bjui-contextmenu.js
 * ========================================================================
 * Copyright 2014 K'naan.
 * Licensed under Apache (http://www.apache.org/licenses/LICENSE-2.0)
 * ======================================================================== */

+function ($) {
    'use strict';
    
    // CONTEXTMENU GLOBAL ELEMENTS
    // ======================
    
    var $menu, $shadow, hash
    
    $(function() {
        var INIT_CONTEXTMENU = function() {
            $menu   = $('<div id="bjui-contextmenu"></div>').hide()
            $shadow = $('<div id="bjui-contextmenuShadow"></div>').hide()
            hash    = []
            
            $('body').append('<!-- contextmenu -->').append($menu).append($shadow)
        }
        
        INIT_CONTEXTMENU()
    })
    
    // CONTEXTMENU CLASS DEFINITION
    // ======================
    var Contextmenu = function(element, options) {
        this.$element = $(element)
        this.options  = options
    }
    
    Contextmenu.DEFAULTS = {
        id       : undefined,
        shadow   : true,
        bindings : {},
        ctrSub   : null
    }
    
    Contextmenu.prototype.init = function() {
        var $this = this
        var op    = this.options
        
        if (!op.id) return
        hash.push({
            id       : op.id,
            shadow   : op.shadow,
            bindings : op.bindings || {},
            ctrSub   : op.ctrSub
        })
        
        var index = hash.length - 1
        
        this.$element.on('contextmenu', function(e) {
            $this.display(index, this, e, op)
            return false
        })
    }
    
    Contextmenu.prototype.display = function(index, trigger, e, options) {
        var $this   = this
        var cur     = hash[index]
        var cp      = BJUI.regional[cur.id]
        var content = FRAG[cur.id]
        
        $.each(cp, function(i, n) {
            content = content.replace('#'+ i +'#', cp[i])
        })
        
        // Send the content to the menu
        $menu.html(content)
        $.each(cur.bindings, function(id, func) {
            $('[rel="'+ id +'"]', $menu).on('click', function(e) {
                $this.hide()
                func($(trigger), $('#bjui-'+ cur.id))
            })
        })
        
        var posX = e.pageX
        var posY = e.pageY
        
        if ($(window).width() < posX + $menu.width())   posX -= $menu.width()
        if ($(window).height() < posY + $menu.height()) posY -= $menu.height()

        $menu.css({'left':posX, 'top':posY}).show()
        if (cur.shadow)
            $shadow.css({width:$menu.width(), height:$menu.height(), left:posX + 3, top:posY + 3}).show()
        $(document).one('click', $this.hide)
        
        if ($.isFunction(cur.ctrSub))
            cur.ctrSub($(trigger), $('#bjui-'+ cur.id))
    }
    
    Contextmenu.prototype.hide = function() {
        $menu.hide()
        $shadow.hide()
    }
    
    // CONTEXTMENU PLUGIN DEFINITION
    // =======================
    
    function Plugin(option) {
        var args     = arguments
        var property = option
        
        return this.each(function () {
            var $this   = $(this)
            var options = $.extend({}, Contextmenu.DEFAULTS, $this.data(), typeof option == 'object' && option)
            var data    = $this.data('bjui.contextmenu')
            
            if (!data) $this.data('bjui.contextmenu', (data = new Contextmenu(this, options)))
            if (typeof property == 'string' && $.isFunction(data[property])) {
                [].shift.apply(args)
                if (!args) data[property]()
                else data[property].apply(data, args)
            } else {
                data.init()
            }
        })
    }

    var old = $.fn.contextmenu

    $.fn.contextmenu             = Plugin
    $.fn.contextmenu.Constructor = Contextmenu
    
    // CONTEXTMENU NO CONFLICT
    // =================
    
    $.fn.contextmenu.noConflict = function () {
        $.fn.contextmenu = old
        return this
    }
    
}(jQuery);