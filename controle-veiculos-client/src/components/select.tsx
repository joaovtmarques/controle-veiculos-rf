"use client";

import {
  Select as ShadcnSelect,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import {
  FormControl,
  FormItem,
  FormMessage,
  FormLabel,
} from "@/components/ui/form";
import { ChangeEvent } from "react";

interface SelectProps {
  label: string;
  options: { label: string; value: string }[];
  value?: string;
  onChange?: (value: string) => void;
  onBlur?: (event: ChangeEvent<HTMLSelectElement>) => void;
  disabled?: boolean;
}

export const Select = ({
  label,
  options,
  value = "",
  onChange,
  disabled = false,
}: SelectProps) => {
  return (
    <FormItem>
      <FormLabel className="text-gray-600">{label}</FormLabel>
      <FormControl>
        <ShadcnSelect
          value={value}
          onValueChange={onChange}
          disabled={disabled}
        >
          <SelectTrigger>
            <SelectValue
              className="text-gray-800 text-muted-foreground"
              placeholder="Selecione"
            />
          </SelectTrigger>
          <SelectContent>
            {options.map((option) => (
              <SelectItem
                className="text-gray-700 hover:bg-gray-100"
                key={option.value}
                value={option.value}
              >
                {option.label}
              </SelectItem>
            ))}
          </SelectContent>
        </ShadcnSelect>
      </FormControl>
      <FormMessage />
    </FormItem>
  );
};
