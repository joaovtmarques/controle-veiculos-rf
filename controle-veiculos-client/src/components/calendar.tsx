/* eslint-disable @typescript-eslint/no-explicit-any */
import { Calendar } from "@/components/ui/calendar";
import {
  FormControl,
  FormItem,
  FormMessage,
  FormLabel,
} from "@/components/ui/form";
import { Controller, type Control } from "react-hook-form";

interface FormCalendarProps {
  control: Control<any>;
  name: string;
  label: string;
}

export const FormCalendar = ({ control, name, label }: FormCalendarProps) => (
  <Controller
    name={name}
    control={control}
    render={({ field }) => (
      <FormItem>
        <FormLabel>{label}</FormLabel>
        <FormControl>
          <Calendar
            mode="single"
            selected={field.value}
            onSelect={field.onChange}
          />
        </FormControl>
        <FormMessage />
      </FormItem>
    )}
  />
);
