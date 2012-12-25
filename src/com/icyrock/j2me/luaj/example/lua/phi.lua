print('> phi')

lcdui = require('lcdui')
display = lcdui.display.get_display(lcdui.midlet.current)

form = lcdui.form.create('phi')

form.set_command_listener(
  function(command, displayable)
    if command.get_label() == 'Exit' then
      lcdui.midlet.current.notify_destroyed()
    end
  end
)

cmd_exit = lcdui.command.create('Exit', 7, 1)
form.add_command(cmd_exit)
cmd_exit = lcdui.command.create('Exit', 7, 2)
form.add_command(cmd_exit)

txt_hi = lcdui.string_item.create('Message', 'Hi from Luaj!')
form.append(txt_hi)

display.set_current(form)

print('< phi')