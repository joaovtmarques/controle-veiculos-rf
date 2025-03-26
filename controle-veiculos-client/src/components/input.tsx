/* eslint-disable @typescript-eslint/no-explicit-any */
import { Input } from "@/components/ui/input";
import {
  FormControl,
  FormItem,
  FormMessage,
  FormLabel,
} from "@/components/ui/form";
import { Controller, type Control } from "react-hook-form";

interface FormInputProps {
  control: Control<any>;
  name: string;
  label: string;
  placeholder?: string;
  type?: string;
}

export const FormInput = ({
  control,
  name,
  label,
  placeholder,
  type = "text",
}: FormInputProps) => (
  <Controller
    name={name}
    control={control}
    render={({ field }) => (
      <FormItem>
        <FormLabel className="text-gray-600">{label}</FormLabel>
        <FormControl>
          <Input
            {...field}
            type={type}
            placeholder={placeholder}
            value={
              field.value instanceof Date
                ? field.value.toISOString().split("T")[0]
                : field.value
            }
            onChange={(e) =>
              field.onChange(
                type === "date" ? new Date(e.target.value) : e.target.value
              )
            }
            className="text-muted-foreground placeholder:text-gray-400"
          />
        </FormControl>
        <FormMessage />
      </FormItem>
    )}
  />
);
