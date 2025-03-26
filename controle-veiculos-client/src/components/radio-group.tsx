/* eslint-disable @typescript-eslint/no-explicit-any */
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import {
  FormControl,
  FormItem,
  FormMessage,
  FormLabel,
} from "@/components/ui/form";
import { Controller, type Control } from "react-hook-form";

interface FormRadioGroupProps {
  control: Control<any>;
  name: string;
  label: string;
  options: { label: string; value: string }[];
  value?: string; // Adicionado
  onChange?: (value: string) => void; // Adicionado
  onBlur?: () => void; // Adicionado
}

export const FormRadioGroup = ({
  control,
  name,
  label,
  options,
  onChange,
}: FormRadioGroupProps) => (
  <Controller
    name={name}
    control={control}
    render={({ field }) => (
      <FormItem>
        <FormLabel className="text-muted-foreground">{label}</FormLabel>
        <FormControl>
          <RadioGroup
            value={field.value} // Passa o valor atual
            onValueChange={(value) => {
              field.onChange(value); // Atualiza o valor do campo
              onChange?.(value); // Chama a função onChange, se existir
            }}
            onBlur={field.onBlur} // Passa o onBlur
            defaultValue={field.value}
            className="flex gap-4"
          >
            {options.map((option) => (
              <div key={option.value} className="flex items-center space-x-2">
                <RadioGroupItem value={option.value} id={option.value} />
                <label htmlFor={option.value} className="text-muted-foreground">
                  {option.label}
                </label>
              </div>
            ))}
          </RadioGroup>
        </FormControl>
        <FormMessage />
      </FormItem>
    )}
  />
);
